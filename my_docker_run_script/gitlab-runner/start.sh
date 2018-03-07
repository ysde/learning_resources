#!/bin/bash
GITLAB_MASTER_IP=xxxx.xxxx.xxx.xxx
CONTAINER_NAME=gitlab-runner
CONFIG_FOLDER=/opt/gitlab-runner/config

REGISTER_TOKEN="xxxxxxxxxxxxxxx"

# If you don't have a public domain, you need to set --docker-extra-hosts for your
GITLAB_DOMAIN="gitlab.xxxxx.xxxx"

status=`systemctl show --property ActiveState docker | cut -d= -f2`

while [ "$status" != "active" ]
do
	echo "sleep 5 sec to wait docker service up"
	sleep 5
	status=`systemctl show --property ActiveState docker | cut -d= -f2`
done

docker rm -f $CONTAINER_NAME
rm -rf ${CONFIG_FOLDER}/*

docker run --net=host -d --name $CONTAINER_NAME --restart always \
  -v ${CONFIG_FOLDER}:/etc/gitlab-runner \
  -v /var/run/docker.sock:/var/run/docker.sock \
  gitlab/gitlab-runner:latest

echo "gitlab-runner container started"
echo "register gitlab-runner"
docker exec ${CONTAINER_NAME} gitlab-runner register -n --url http://${GITLAB_MASTER_IP} --registration-token ${REGISTER_TOKEN} --executor docker --docker-image docker:latest --docker-extra-hosts "${GITLAB_DOMAIN}:${GITLAB_MASTER_IP}" --docker-privileged --docker-volumes "/var/run/docker.sock:/var/run/docker.sock" "/cache"
echo "finish registering gitlab-runner."

sleep 5
echo "modify gitlab-runner global setting: concurrent to 10"
sed -i -e 's/concurrent = 1/concurrent = 10/g' ${CONFIG_FOLDER}/config.toml

echo "restart gitlab-runner"
docker restart ${CONTAINER_NAME}

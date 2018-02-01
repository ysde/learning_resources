#!/bin/bash

# https://docs.gitlab.com/runner/install/docker.html

CONTAINER_NAME=gitlab-runner
CONFIG_FOLDER=/srv/gitlab-runner/config/config.toml

docker rm -f $CONTAINER_NAME

docker run --net=host -d --name $CONTAINER_NAME --restart always \
  -v ${CONFIG_FOLDER}:/etc/gitlab-runner \
  -v /var/run/docker.sock:/var/run/docker.sock \
  gitlab/gitlab-runner:latest

sleep 2

docker exec -it $CONTAINER_NAME gitlab-runner register

# set extra host, otherwise gitlab-runner doesn' know YOUR_DOMAIN even you set the /etc/hosts
# sed -i '/\[runners.docker\]/a \ \ \ \ extra_hosts = ["YOUR_DOMAIN:ip_address"]' ${CONFIG_FOLDER}/config.toml

docker restart $CONTAINER_NAME

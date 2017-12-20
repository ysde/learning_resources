#!/bin/bash

CONTAINER_NAME="nagios"

docker rm -f "${CONTAINER_NAME}"

docker run --name ${CONTAINER_NAME} -d \
--net=host \
-e REGION={{ region }} \
-v /opt/nagios/conf/CloudOPS/{{ region }}:/usr/local/nagios/etc/servers \
-v /opt/nagios/conf/common_servers:/usr/local/nagios/etc/common_servers \
-v /opt/nagios/conf/libexec:/usr/local/nagios/libexec \
-v /opt/nagios/conf/nagios.cfg:/usr/local/nagios/etc/nagios.cfg \
-e NAGIOS_PORT={{ exposed_port }} \
docker.io/ysde/docker-nagios:4.3.2

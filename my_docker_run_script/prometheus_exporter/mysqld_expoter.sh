#!/bin/bash

MYSQL_IP=127.0.0.1
MYSQL_PORT=3306
USER_ACCOUNT=foo
PASSWORD=bar
EXPORT_PORT=9104

docker run --name mysql_exporter -d \
-p ${EXPORT_PORT}:9104 \
-e DATA_SOURCE_NAME="${USER_ACCOUNT}:${PASSWORD}@(${MYSQL_IP}:${MYSQL_PORT})/" \
prom/mysqld-exporter \
-e 

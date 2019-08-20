#!/usr/bin/env bash
# https://hub.docker.com/_/redis

CONTAINER_NAME=redis
CONTAINER_CONF_PATH=/usr/local/etc/redis/redis.conf

DATA_FOLDER=/tmp/data
mkdir $DATA_FOLDER
cp redis.conf /tmp/

docker rm -f $CONTAINER_NAME

# linux version
docker run --name $CONTAINER_NAME -d \
-v /tmp/data:/data \
-v /tmp/redis.conf:${CONTAINER_CONF_PATH} \
redis \
redis-server ${CONTAINER_CONF_PATH}

# mac version
docker run --name $CONTAINER_NAME -d -p 6379:6379 redis

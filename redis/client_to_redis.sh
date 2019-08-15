#!/bin/bash

CONTAINER_NAME=redis
REDIS_BIND_HOST=127.0.0.1 # Modify this according to redis.conf
REDIS_PASSWORD=12345678

# mac versioin
docker run -it --net=host --rm $CONTAINER_NAME redis-cli -h $REDIS_BIND_HOST -a $REDIS_PASSWORD

# linux version
docker run -it --rm $CONTAINER_NAME redis-cli -h $REDIS_BIND_HOST -a $REDIS_PASSWORD

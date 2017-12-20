#!/bin/bash

docker rm -f "{{ container_name }}"

docker run --name={{ container_name }} -d \
-p {{ exposed_port }}:9145 \
ysde/docker-aerospike-exporter \
-node {{ ansible_eth0['ipv4']['address'] }}:3000

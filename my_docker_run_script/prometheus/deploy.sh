#!/bin/bash
image="{{ image_version }}"
container_name="{{ container_name }}"

# pull image
docker pull $image

# stop container
docker stop $container_name

# restart container
docker ps -a|grep $container_name && docker rm -f $container_name

docker run \
-d \
--net=host \
--restart=always \
-v /home/prometheus-plugin/{{ container_name }}/conf/prometheus.yml:/etc/prometheus/prometheus.yml \
-v /home/prometheus-plugin/{{ container_name }}/data/:/prometheus/data/ \
-v /etc/localtime:/etc/localtime \
--name $container_name \
$image \
-web.listen-address=":{{ prometheus_port }}" \
-config.file=/etc/prometheus/prometheus.yml \
-storage.local.series-file-shrink-ratio={{ file_shrink_ratio }} \
-storage.local.retention={{ retention_time }}

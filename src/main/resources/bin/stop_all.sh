#!/bin/bash

cd `dirname $0`

# stop grafana
echo "stop grafana"
./stop_grafana.sh

# stop prometheus
echo "stop prometheus"
./stop_prometheus.sh

# stop consul
echo "stop consul"
./stop_consul.sh


# stop node_exporter
echo "stop node_exporter"
./stop_node_exporter.sh


echo "see logs at /data/monitor/log"



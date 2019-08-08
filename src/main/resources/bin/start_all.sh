#!/bin/bash

cd `dirname $0`
# start node_exporter
echo "start node_exporter"
./start_node_exporter.sh

#start consul
echo "start consul"
./start_consul.sh

#start prometheus
echo "start prometheus"
./start_prometheus.sh

#start grafana
echo "start grafana"
./start_grafana.sh

echo "see logs at /data/monitor/log"
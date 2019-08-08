#!/bin/bash
monitorpath="/data/monitor"
cd ${monitorpath}/exporter/node_exporter

echo "start node_exporter at : `date` " > ${monitorpath}/log/node_exporter_runtime.log
nohup ${monitorpath}/exporter/node_exporter/node_exporter > ${monitorpath}/log/node_exporter.log 2>&1 &
echo $! > ${monitorpath}/log/node_exporter.pid
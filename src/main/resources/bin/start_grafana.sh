#!/bin/bash

monitorpath="data/monitor"
cd ${monitorpath}/app/grafana
echo "start grafana at : `date` " >${monitorpath}/log/grafana_runtime.log
nohup ${monitorpath}/app/grafana/bin/grafana-server --config ${monitorpath}/cfg/grafana/custom.ini > ${monitorpath}/log/grafana.log 2>&1 &
echo $! > ${monitorpath}/log/grafana.pid
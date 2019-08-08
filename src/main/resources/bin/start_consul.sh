#!/bin/bash

monitorpath="/data/monitor"
cd ${monitorpath}/app/consul

echo "start consul at : `date` " > ${monitorpath}/log/consul_runtime.log
nohup ${monitorpath}/app/consul/consul agent -server -config-dir="${monitorpath}/cfg/consul" > ${monitorpath}/log/consul.log 2>&1 &
echo $! > ${monitorpath}/log/consul.pid
#!/bin/bash
monitorpath="/data/monitor"
cd ${monitorpath}/app/prometheus

echo "start prometheus at : `date` " > ${monitorpath}/log/prometheus_runtime.log
nohup ${monitorpath}/app/prometheus/prometheus --web.listen-address=:7002 \
        --config.file=${monitorpath}/cfg/prometheus/prometheus.yml \
        --web.read-timeout=5m \
        --web.enable-admin-api \
        --web.max-connection=10 \
        --query.timeout=2m \
        --query.max-concurreny=20 \
        --storage.tsdb.path=${monitorpath}/data/prometheus/ > ${monitorpath}/log/prometheus.log 2>&1 &
echo $! > ${monitorpath}/log/prometheus.pid
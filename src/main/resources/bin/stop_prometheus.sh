#!/bin/bash

echo "stop prometheus at : `date` " > /data/monitor/log/prometheus_runtime.log
if [ -f "/data/monitor/log/prometheus.pid" ]; then
    kill -9 `cat /data/monitor/log/prometheus.pid`
    rm /data/monitor/log/prometheus.pid
else
    echo "file prometheus.pid not exist"
fi
#!bin/bash

echo "stop program at : `date` " > /data/monitor/log/grafana_runtime.log
if [ -f "/data/monitor/log/grafana.pid" ]; then
    kill -9 `cat /data/monitor/log/grafana.pid`
    rm /data/monitor/log/grafana.pid
else
    echo "file grafana.pid not exist"
fi
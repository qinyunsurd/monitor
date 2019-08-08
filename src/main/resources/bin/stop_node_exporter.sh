#!/bin/bash

echo "stop node_exporter at : `date` " > /data/monitor/log/node_exporter_runtime.log
if [ -f "/data/monitor/log/node_exporter.pid" ]; then
    kill -9 `cat /data/monitor/log/node_exporter.pid`
    rm /data/monitor/log/node_exporter.pid
else
    echo "file node_exporter.pid not exist"
fi
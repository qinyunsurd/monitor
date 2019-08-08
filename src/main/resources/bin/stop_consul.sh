#!/bin/bash

echo "stop consul at: `date` " > /data/monitor/log/consul_runtime.log
if [ -f "/data/monitor/log/consul.pid" ]; then
    kill -9 `cat /data/monitor/log/consul.pid`
    rm /data/monitor/log/consul.pid
else
    echo "file consul.pid not exist"
fi
#!/bin/bash

installdir="/data/monitor"
shpath=$0
toolsdir=${shpath%/*}
echo "$toolsdir"
cd $toolsdir
echo "======================unpackaging monitor app to $installdir/app ========================="
mkdir -p $installdir/app
rm -r -f $installdir/app/*
mkdir -p $installdir/app/grafana
mkdir -p $installdir/app/prometheus
mkdir -p $installdir/app/consul

tar -zxvf grfana-6.1.3.linux-amd64.tar.gz -C $installdir/app/grafana
mv $installdir/app/grafana/grafana*/*   $installdir/app/grafana
rmdir $installdir/app/grafana/grafana*

tar -zxvf prometheus-2.8.1.linux-amd64.tar.gz -C $installdir/app/prometheus
mv $installdir/app/prometheus/prometheus*/* $installdir/app/prometheus
rmdir $installdir/app/prometheus/prometheus-*

unzip consul_1.4.4_linux_amd64.zip -d $installdir/app/consul

mkdir -p $installdir/data
rm -r -f $installdir/data/*
mkdir -p $installdir/data/consul
mkdir -p $installdir/data/prometheus
mkdir -p $installdir/data/grafana
mkdir -p $installdir/cfg
rm -r -f $installdir/cfg/*
mkdir -p $installdir/cfg/consul
mkdir -p $installdir/cfg/prometheus
mkdir -p $installdir/cfg/grafana

mkdir -p $installdir/log
mkdir -p $installdir/bin
echo "==============================unpackage monitor app success  ============================="
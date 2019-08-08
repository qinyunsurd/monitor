#!/bin/bash

installdir="/data/monitor"
shpath=$0
toolsdir=${shpath%/*}
echo "$toolsdir"
cd $toolsdir
echo "======================unpackaging monitor exporter to $installdir/exporter ========================="
mkdir -p $installdir/exporter
mkdir -p $installdir/exporter/node_exporter
rm -r -f $installdir/exporter/node_exporter/*
mkdir -p $installdir/log
mkdir -p $installdir/bin

tar -zxvf node_exporter-0.17.0.linux-amd64.tar.gz -C $installdir/exporter/node_exporter
mv $installdir/exporter/node_exporter/node_exporter*/*   $installdir/exporter/node_exporter
rmdir $installdir/exporter/node-exporter/node_exporter-*


echo "==============================unpackage monitor exporter success  ============================="
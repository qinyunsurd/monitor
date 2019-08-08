#!/bin/bash

installdir="/data/monitor"
shpath=$0
toolsdir=${shpath%/*}
echo "$toolsdir"
cd $toolsdir
echo "======================uninstall monitor exporter to $installdir/exporter ========================="

rm -r -f $installdir/exporter/node_exporter

echo "==============================unpackage monitor exporter success  ============================="
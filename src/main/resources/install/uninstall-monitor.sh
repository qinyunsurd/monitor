#!/bin/bash

installdir="/data/monitor"
shpath=$0
toolsdir=${shpath%/*}
echo "$toolsdir"
cd $toolsdir

echo "==========================uninstall app to $installdir/app=============================="

rm -r -f $installdir/app
rm -r -f $installdir/cfg
rm -r -f $installdir/data
rm -r -f $installdir/log

echo "==========================uninstall app success=============================="
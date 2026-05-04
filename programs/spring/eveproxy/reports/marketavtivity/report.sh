#!/bin/bash

# requires an entry for a service named "eveproxy" in your  ~/.pg_service.conf .
# https://www.postgresql.org/docs/current/libpq-pgservice.html

printf -v date '%(%Y-%m-%d)T' -1
rootdir=$(cd `dirname "$0"` && pwd)
# rm -rf "$rootdir/$date"
mkdir "$rootdir/$date" || exit 1
echo "writing report in $rootdir/$date"
date -u -I seconds >> "$rootdir/$date/traces.log"

for report_name in diffactivity typesactivity botorders ; 
do
	echo "querying $report_name"
	date_start=$SECONDS
	psql 'service=eveproxy' -f "$rootdir/sql/$report_name.sql" --csv -o "$rootdir/$date/$report_name.csv"
	date_end=$SECONDS
	log_str="$report_name in $((date_end-date_start)) seconds"
	echo " $log_str"
	echo $log_str >> "$rootdir/$date/traces.log"
done

# chart the activity
gnuplot <<EOF
set term svg font "arial,12" fontscale 1.0 size 2000, 1000
set output '$rootdir/$date/diffactivity.svg'
set datafile separator ','
set key autotitle columnhead
set xdata time
set timefmt '%Y-%m-%d %H:%M:%S+'
set autoscale
plot '$rootdir/$date/diffactivity.csv' u 1:2 with lines title columnhead,\
	'$rootdir/$date/diffactivity.csv' u 1:3 with lines title columnhead,\
	'$rootdir/$date/diffactivity.csv' u 1:4 with lines title columnhead
EOF

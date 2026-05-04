#!/bin/bash

# requires an entry for a service named "eveproxy" in your  ~/.pg_service.conf .
# https://www.postgresql.org/docs/current/libpq-pgservice.html

printf -v date '%(%Y-%m-%d)T' -1
rootdir=$(cd `dirname "$0"` && pwd)
echo "writing report in $rootdir/$date"
mkdir "$rootdir/$date" || exit 1
date -u -I seconds >> "$rootdir/$date/traces.log"

for report_name in typesactivity botorders diffactivity;
do
	echo "querying $report_name"
	date_start=$SECONDS
	psql 'service=eveproxy' -f "$rootdir/sql/$report_name.sql" --csv -o "$rootdir/$date/$report_name.csv"
	date_end=$SECONDS
	log_str="$report_name in $((date_end-date_start)) seconds"
	echo " $log_str"
	echo $log_str >> "$rootdir/$date/traces.log"
done

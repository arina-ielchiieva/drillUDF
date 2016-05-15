#!/bin/bash

home=/arina
log_location=$home/logs
query_location=$home/queries

for i in `seq 1 21`;
do
jar_name='DrillUDF-'$i'.0.jar'
query="create function using jar '"$jar_name"'"
query_file=$query_location/query_$i.sql
echo $query >> $query_file
chmod 777 $query_file
/opt/mapr/drill/master/bin/sqlline -u "jdbc:drill:zk=master:5181;" -f "$query_file" >> $log_location/$i.txt &
done
wait

exit 0
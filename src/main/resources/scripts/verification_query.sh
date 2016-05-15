#!/bin/bash

query='select '
for i in `seq 1 21`;
do
suffix='_'$i'_5'
function_name='custom_lower'$suffix
column='column'$suffix
upper_text='A'$suffix
query="$query$function_name('$upper_text') as $column"
if [ "$i" -ne "21" ]
then
  query=$query', '
fi
done

query=$query" from (values(1))"
echo $query
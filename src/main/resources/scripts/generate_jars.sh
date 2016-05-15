#!/bin/bash

home=/arina
jars_location=$home/jars
repo_location=/git_repo/drillUDF
class_location=$repo_location/src/main/java/com/drill/udf/CustomLowerFunction.java
target_location=$repo_location/target
original_function_name=custom_lower

cd $repo_location

for i in `seq 1 21`;
do
new_function_name=$original_function_name'_'$i
sed -i -e "s/$original_function_name/$new_function_name/g" $class_location
my_version=$i'.0'
mvn clean install -D=my.version=$my_version
binary_name='DrillUDF-'$my_version'.jar'
source_name='DrillUDF-'$my_version'-sources.jar'
cp $target_location/$binary_name $jars_location
cp $target_location/$source_name $jars_location
sed -i -e "s/$new_function_name/$original_function_name/g" $class_location
done

exit 0
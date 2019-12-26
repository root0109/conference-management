#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=target
##jar file name
jar_name=conferencemgmt.jar

mvn clean install

if [ -z "$1" ] ; then
	echo "File Path as Argument is mandatory. Usage java -jar <jar_full_path>  <file_path>"
    exit 1
else
	java -jar $dir/$jar_name $arg1
fi

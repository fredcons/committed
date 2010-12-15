#!/bin/sh
#export JAVA_HOME=/usr/local/jdk
SCRIPT_DIR=$(dirname $0)
echo "SCRIPT_DIR : ${SCRIPT_DIR}"
$JAVA_HOME/bin/java  "-Dlog4j.configuration=file:./conf/log4j.properties" -jar lib/committed-batch-1.0-SNAPSHOT.jar $*

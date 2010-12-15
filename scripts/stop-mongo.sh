#!/bin/bash
MONGO_PID=`pidof mongod`
echo "Found mongod pid : $MONGO_PID"
if [ -n "$MONGO_PID" ]; then
    kill $MONGO_PID
fi

#!/bin/bash

if [ "$1" == "start" ]; then
    nohup sh /Users/kchen/Opensrc/java/incubator-rocketmq/target/apache-rocketmq-all/bin/mqnamesrv &
elif [ "$1" == "stop" ]; then
    nohup sh /Users/kchen/Opensrc/java/incubator-rocketmq/target/apache-rocketmq-all/bin/mqshutdown namesrv
else
    echo "usage: namesvr [start|stop]"
fi

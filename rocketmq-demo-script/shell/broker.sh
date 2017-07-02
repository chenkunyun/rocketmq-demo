#!/bin/bash

if [ "$1" == "start" ]; then
    nohup sh $HOME/Opensrc/java/incubator-rocketmq/target/apache-rocketmq-all/bin/mqbroker -n localhost:9876 &
elif [ "$1" == "stop" ]; then
    nohup sh $HOME/Opensrc/java/incubator-rocketmq/target/apache-rocketmq-all/bin/mqshutdown broker
else
    echo "usage: broker [start|stop]"
fi

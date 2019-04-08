#!/bin/bash

cmd1='PORT=9777 java -Dspring.profiles.active=dev -jar target/discovery-service-2-0.0.1-SNAPSHOT.jar'

echo ${cmd1}
nohup "${cmd1}" &
#nohup java -Dspring.profiles.active=dev -jar target/discovery-service-2-0.0.1-SNAPSHOT.jar



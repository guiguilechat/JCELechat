#!/bin/bash

docker-compose down\
 && docker-compose build --no-cache 2>&1 | tee dockerbuild.log\
 && docker-compose up 2>&1 | tee dockerup.log
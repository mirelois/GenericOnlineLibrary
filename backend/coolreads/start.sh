#!/bin/bash

NETWORK_NAME="coolreads_net"


if ! docker network inspect $NETWORK_NAME &>/dev/null; then
    echo "Creating network $NETWORK_NAME"
    docker network create $NETWORK_NAME
else
    echo "Network $NETWORK_NAME already exists!"
fi

check() {

local name="$1"

if docker ps -a --filter "name=$name" --format '{{.Names}}' | grep -q "$name"; then
        echo "Container $name already running, stopping..."
        docker stop $name >/dev/null
        docker rm $name >/dev/null
fi
}

check postgres

./scripts/startDB.sh

check redis

docker run --name redis --net $NETWORK_NAME -p 6379:6379 -d redis

if [ -z $1 ]; then
    echo "Not running coolreads container"
else
    check coolreading
    docker run --name coolreading --net $NETWORK_NAME -p 8080:8080 -e PROFILE=$1 -d coolreads
fi

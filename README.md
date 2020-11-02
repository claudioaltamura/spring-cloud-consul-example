# spring-cloud-consul-example

A Spring Cloud Consul example

## Build

    mvn clean compile

## Run

    docker-compose up -d

    mvn spring-boot:run

## Stop

    docker-compose down -v

## Examples

Rest

    curl http://localhost:8080/helloworld

Service Discovery

    curl http://localhost:8080/discoverYHost
    curl http://localhost:8080/discoveryExample

Distributed Configuration

    curl http://localhost:8080/getConfigFromValue

    curl http://localhost:8080/getConfigFromProperty

## Consul
https://hub.docker.com/_/consul

https://hub.docker.com/r/bitnami/consul/

UI

    http://localhost:8500/

Commands

    docker exec -it consul bash

        consul kv get config/blueprint/greetings/message

        ...

Healthcheck

    curl http://localhost:8500/v1/health/service/consul

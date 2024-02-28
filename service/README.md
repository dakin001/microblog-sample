
# Getting Started
this is backend service.

## Dependencies

1. database H2 default
2. redis

## How to start

1. install Docker Desktop
> https://www.docker.com/products/docker-desktop/

2. start local third party services (redis)
```shell
cd scripts 
docker-compose up -d
```
3. start application
```shell
./gradlew build
java -jar build/libs/app-server.jar
```

4. open swagger-ui  
> http://localhost:8080/swagger-ui 

## Other 
1. build docker image
in project root folder
```shell
docker build -t moikiitos/service:v1 .
```

2. deployment in kubernetes
```shell
kubectl apply -f scripts/k8s/deployment.yaml
kubectl apply -f scripts/k8s/service.yaml
```
This example demonstrates how to run Spring Boot application with Quartz in cluster mode.

Also in this example we autowire service that currently runs on the instance where jobs executed.

## Description of modules
### Supervisor module
The supervisor module have Swagger ui with REST commands. It available at [http://localhost:8080/swagger-ui.html#!/](http://localhost:8080/swagger-ui.html#!/)
You can use this commands to add jobs, view jobs statuses and delete jobs.

### Worker module
Executes tasks that submited to the cluster.

## How to run
Before you start `supervisor` and `worker` apps you need to start MySQL database, to do that you can
 use `docker-compose.yml` file and execute in the project directory: 

to start mysql:
```
docker-compose up -d
```
to build images
```
docker-compose -f docker-compose-to-build-images.yml build -d
```
to build and start images
```
docker-compose -f docker-compose-to-build-images.yml up --build -d
``` 
 
After that you need to run first `supervisor` it will creates necessary database(`test`) and tables
 for quartz. 
After it starts run `worker`.

After that open in your browser swagger ui [http://localhost:8080/swagger-ui.html#!/](http://localhost:8080/swagger-ui.html#!/)


# Docker

    https://www.howtoforge.com/tutorial/how-to-create-docker-images-with-dockerfile/
    https://www.sitepoint.com/how-to-build-an-image-with-the-dockerfile/

### building images

    docker build -t cluster/quartz-supervisor .
    docker run -p 8080:8080 -t cluster/quartz-supervisor --name quartz-supervisor
    docker run -p 8080:8080 -t cluster/quartz-supervisor --name quartz-supervisor

### more commands

    mvn package spring-boot:repackag


## networks

see `docker-compose.yml` network configuration

    docker network connect quartz_network quartz_supervisor
    
    docker-compose up build --no-cache -d
    docker-compose build --no-cache
    docker-compose down
    docker info
    docker node ls
    docker swarm --help
    docker swarm leave
    docker swarm leave --force
    docker info
    docker swarm init --help
    docker swarm init --advertise-addr 127.0.0.1

To add a worker to this swarm, run the following command:

    docker swarm join --token SWMTKN-1-2vp1s6sumhvszonqmyb7yv3b2qaf3d6gm8qpvq9lxmtkr40us5-d0jsp9tjwwciy1fp4bp5wy4bc 127.0.0.1:2377


    docker-compose build --no-cache
    docker stack deploy quartz_cluster -c docker-swarm.yml
    docker ps
    docker service ls
    docker service --help
    docker service scale quartz_cluster_quartz-worker=3
    docker events --help
    docker events --since 30m
    docker stack rm quartz_cluster
    docker service logs quartz_cluster_quartz-worker -f

This example demonstrates how to run Spring Boot application with Quartz in cluster mode.

Also in this example, we autowire service that currently runs on the instance where jobs are executed.

## Description of modules
### Supervisor module
The supervisor module has Swagger ui with REST commands. It is available at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
You can use this commands to add jobs, view jobs statuses and delete jobs.

### Worker module
Execute tasks that are submitted to the cluster.

## How to run
Before you start `supervisor` and `worker` apps you need to start MySQL database, to do that you can
 use `docker-compose.yml` file and execute in the project directory: 
```
docker-compose up -d
``` 
 
After that you need to run first `supervisor` it will create necessary database(`test`) and tables
 for quartz. 
After it starts run `worker`.

After that open in your browser swagger ui [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

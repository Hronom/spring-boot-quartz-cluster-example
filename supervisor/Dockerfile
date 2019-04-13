##FROM openjdk:8-jdk-alpine
##VOLUME /tmp
##ARG DEPENDENCY=target/dependency
##COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
##COPY ${DEPENDENCY}/META-INF /app/META-INF
##COPY ${DEPENDENCY}/BOOT-INF/classes /app
##ENTRYPOINT ["java","-cp","app:app/lib/*","com.github.hronom.spring.boot.quartz.cluster.example.supervisor.SupervisorApp"]

FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
ADD /target/*.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dock","/app.jar"]

#FROM java:8
#EXPOSE 8080
#ADD /target/*.jar demo.jar
#ENTRYPOINT ["java","-jar","demo.jar"]

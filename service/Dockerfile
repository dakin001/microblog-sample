FROM amazoncorretto:17
LABEL name="moikiitos-service"
COPY build/libs/app-server.jar app-server.jar
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java -XX:MaxRAMPercentage=99 $JAVA_OPTS -Dspring.profiles.active=$PROFILE -jar /app-server.jar" ]

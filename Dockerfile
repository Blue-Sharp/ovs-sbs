FROM openjdk:8

ARG JAR_FILE
ADD ${JAR_FILE} /ovs.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/ovs.jar"]

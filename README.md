# sbs
Studenten bilden Schüler (SBS) Online-Verwaltungsssystem (OVS) und API

## Requirements

- Java JDK 1.8
- Maven 3.5

## Quickstart

```bash
git clone https://github.com/Blue-Sharp/sbs.git
cd sbs
mvn clean install # or use the provided wrapper mvnw
```
Start Application
```bash
java --spring.profiles.active=local -jar target/ovs-0.0.1-SNAPSHOT.jar # change default port with -Dserver.port=80
```

Build and run docker image
```bash
mvn install dockerfile:build
docker run --name ovs -p 8080:8080 -e spring.profiles.active=local ovs
```


Visit in your webbrowser `localhost:8080`

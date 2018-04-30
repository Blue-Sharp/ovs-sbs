# sbs
Studenten bilden Schüler (SBS) Online-Verwaltungsssystem (OVS) und API

## Requirements

- Java JDK 1.8
- Maven 3.5

## Quickstart
```bash
git clone https://github.com/Blue-Sharp/sbs.git
cd sbs
mvn clean install
java --spring.profiles.active=local -jar target/ovs-0.0.1-SNAPSHOT.jar # change default port with -Dserver.port=80
```

Visit in your webbrowser `localhost:8080`

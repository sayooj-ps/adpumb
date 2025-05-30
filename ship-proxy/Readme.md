# ship-proxy

Application that represents the ship's proxy or the proxy client.
Uses Java 17, Spring boot 3 with Gradle.

### Tech Stack

- Java 17+
- Spring Boot 3.5
- Gradle
- Docker (optional)

### Prerequisites

- Java JDK 17+
- Gradle
- (Optional) Docker

### How to run using docker
- Make sure the offshore proxy application is running in docker
- Change the `offshore.proxy.server.host` property value in `applications.properties` file accordingly. If running in local machine, set it as `localhost`. If using docker, use `offshore-proxy`
- Then clean and build the jar using the following command in terminal
```
For linux:
./gradlew clean build

For windows:
gradlew clean build
```
- Then execute the following commands
```
docker build -t ship-proxy .
docker run -p 8080:8080 --network ship-network --name ship-proxy ship-proxy
```
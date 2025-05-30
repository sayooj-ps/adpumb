# offshore-proxy

Application that represents the offshore server or the proxy server.
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
- Clean and build the jar using the following command in terminal
```
For linux:
./gradlew clean build

For windows:
gradlew clean build
```
- Then execute the following commands
```
docker build -t offshore-proxy .
docker network create ship-network
docker run -p 8080:8080 --network ship-network --name offshore-proxy offshore-proxy
```
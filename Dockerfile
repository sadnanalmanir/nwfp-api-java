FROM ubuntu:latest
LABEL authors="sadnan"

# build
FROM maven as build
WORKDIR /usr/src/app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline
COPY . .
RUN mvn -B -e -o -T 1C verify

# package without maven
FROM openjdk
COPY --from=build /usr/src/app/target/*.jar ./
CMD ["java", "-cp", "nwfp-api-java-1.0-SNAPSHOT.jar", "uk.ac.rothamsted.ide.GetCatchment"]
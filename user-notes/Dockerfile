FROM docker.io/9698629519/demo-registry:openjdk-11
WORKDIR /prana
COPY build/libs/user-notes-latest.jar /prana
WORKDIR /prana
EXPOSE 8080
CMD ["java", "-jar", "user-notes-latest.jar"]

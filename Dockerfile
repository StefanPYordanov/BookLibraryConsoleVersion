FROM openjdk:17

WORKDIR /app

COPY target/BookLibraryConsoleVersion-2.0-SNAPSHOT-jar-with-dependencies.jar /app/book-library-app.jar

ENTRYPOINT ["java", "-jar", "/app/book-library-app.jar"]

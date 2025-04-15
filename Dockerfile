FROM openjdk:17
WORKDIR /app

COPY target/BookLibraryConsoleVersion-1.0-SNAPSHOT-jar-with-dependencies.jar /app/book-library-app.jar
COPY start.sh /start.sh

RUN chmod +x /start.sh

ENTRYPOINT ["/start.sh"]
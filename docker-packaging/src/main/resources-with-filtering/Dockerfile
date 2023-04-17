FROM amazoncorretto:17-al2023-headless

WORKDIR /app/chess
COPY lib/* lib/
COPY conf/* conf/
EXPOSE 8080

ENTRYPOINT java @conf/launch_options
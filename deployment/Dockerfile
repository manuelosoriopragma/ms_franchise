FROM amazoncorretto:21-alpine@sha256:2622b23e1a0287ab6e2a0abb0faab15f4070716dd5e6a70bebf1b1dfd48bd22a
ARG arg_api_port
ENV ENV_API_PORT=$arg_api_port
EXPOSE $ENV_API_PORT

RUN apk add --no-cache tzdata && \
    cp /usr/share/zoneinfo/America/Bogota /etc/localtime && \
    echo "America/Bogota" > /etc/timezone && \
    addgroup -S appgroup && adduser -S appuser -G appgroup

USER appuser

VOLUME /tmp

COPY ./applications/app-service/build/libs/*.jar app.jar

ENV JAVA_OPTS=" -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]
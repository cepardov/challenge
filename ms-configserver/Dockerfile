FROM amazoncorretto:21-alpine

RUN apk update && apk --no-cache add tzdata
ENV TZ=America/Santiago

EXPOSE 8080

COPY build/libs/app.jar /app/app.jar

WORKDIR /app
CMD ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom ${java_ops} -jar app.jar"]

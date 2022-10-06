#FROM amd64/openjdk:11-jdk
#
#WORKDIR /home/spring
#
#COPY build/libs/*.jar /home/spring/application.jar
#
#EXPOSE 8080
#
#CMD ["java", "-jar", "/home/spring/application.jar"]

FROM amd64/openjdk:11
VOLUME /tmp
ADD build/libs/chatting-0.0.1-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
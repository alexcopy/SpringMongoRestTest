FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    STUDENTS_SLEEP=0 \
    JAVA_OPTS=""


RUN adduser -D -s /bin/sh student
WORKDIR /home/student


ADD entrypoint.sh entrypoint.sh
RUN chmod 755 entrypoint.sh && chown student:student entrypoint.sh
USER student

ENTRYPOINT ["./entrypoint.sh"]

EXPOSE 8080

ADD *.war app.war

CMD echo "The application will start in ${STUDENTS_SLEEP}s..." && \
    sleep ${STUDENTS_SLEEP}

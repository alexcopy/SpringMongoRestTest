FROM openjdk:8

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    STUDENTS_SLEEP=0 \
    JAVA_OPTS=""

# add source

ADD . /code/
# package the application and delete all lib
RUN echo '{ "allow_root": true }' > /root/.bowerrc && \
    cd /code/ && \
    ./mvnw clean package  -DskipTests && \
    mv /code/target/*.war /app.war && \
    rm -Rf /code /root/.npm/ /tmp && \
    rm -Rf /root/.m2/

RUN sh -c 'touch /app.war'
VOLUME /tmp

EXPOSE 8080:8080
EXPOSE 8081:8888


CMD echo "The application will start in ${STUDENTS_SLEEP}s..." && \
    sleep ${STUDENTS_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.war


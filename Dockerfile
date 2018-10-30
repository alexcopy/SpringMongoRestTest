FROM openjdk:8

ENV STUDENTS_SLEEP 0

# add source
ADD . /code/
# package the application and delete all lib
RUN echo '{ "allow_root": true }' > cd /code/ && \
    ./mvnw clean package -DskipTests && \
    mv /code/target/*.war /app.war && \
    rm -Rf /root/.m2/

RUN sh -c 'touch /app.war'
VOLUME /tmp
EXPOSE 8080
CMD echo "The application will start in ${STUDENTS_SLEEP}s..." && \
    sleep ${STUDENTS_SLEEP} && \
    java -Djava.security.egd=file:/dev/./urandom -jar /app.war


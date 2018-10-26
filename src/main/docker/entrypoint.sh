#!/bin/sh

echo "The application will start in ${STUDENTS_SLEEP}s..." && sleep ${STUDENTS_SLEEP}
exec java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar "${HOME}/app.war" "$@"

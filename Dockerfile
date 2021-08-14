FROM amazoncorretto:11
ARG profile
ARG db_username
ARG db_password
ARG okta_apikey
ARG JAR_FILE=target/*.jar
ENV profile=${profile}
ENV username=${db_username}
ENV password=${db_password}
ENV okta_apikey=${okta_apikey}
EXPOSE 8080/tcp
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${profile}", "-Ddb_username=${username}", "-Ddb_password=${password}", "-Dconstants.okta.apikey=${okta_apikey}", "/app.jar"]
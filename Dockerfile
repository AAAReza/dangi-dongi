FROM ibm-semeru-runtimes:open-21-jre
RUN mkdir /opt/app
EXPOSE 8080
COPY target/*.jar /opt/app/app.jar
CMD ["java", "-jar", "/opt/app/app.jar"]

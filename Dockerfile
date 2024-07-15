FROM ibm-semeru-runtimes:open-21-jre
RUN mkdir /opt/app
COPY target/*.jar /opt/app
CMD ["java", "-jar", "/opt/app/*.jar"]

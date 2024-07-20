## Dangi-Dongi Application

-----

Dangi-Dongi is a bill sharing and sharing application that anyone can share the bill or any expenses they have paid with their friends so that everyone pays according to their share.

Step Of Using Dangi-Dongi App:

- register your info and add to system as user
- Create Group of your friend
- Add friend to group
- Add bill info to group
- See your share and pay it
- get all info of group, bill, share of anyone by calling service

----

## Project Specification

* Spring boot with java 21
* Embedded H2 Database
* liquibase for database changes



## Run

* run mvm clean install
and execute:  java -jar target/Dangi-Dongi-0.0.1-SNAPSHOT.jar

* application run on port 8080 . you can see document of service in http://localhost:8080/swagger-ui/index.html

### Docker Image
for create docker image can run docker build -t {image-name) .

or use mvn spring-boot:build-image build image with build pack


### Execute Postman file

run postman collection file in postman directory and execute all test in collection by running collection test


## Future Work

* write pipeline for CI/CD integration and automation release
* add profiling of application for different environment
* use an identity providers like keycloak or spring authorization and migrate to oauth2 from basic authentication and authorization
* add new integration and postman test for other test scenario
* connect to an code analyzer like sonarqube for check code quality
* migrate to microservice and spring cloud by :
* * enable service discovery/registry like consul in development and kubernetes service discovery in production
* * add gateway module service for authentication/authorization by connecting to identity providers and routing to service module
* * use feign client for internal service communication
* create an client application like UI with angular and mobile application
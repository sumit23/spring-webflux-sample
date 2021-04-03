# spring-webflux-sample
A sample REST API application based on spring webflux

## Local setup
The application needs postgres to run. There is just one table for which the create SQL query is mentioned in `src/main/resources/data.sql`.

Compile the application with `mvn clean install`

Run the application with `mvn spring-boot:run`

## Swagger
The application has swagger 2 integrated. Access the swagger at

[Swagger Local URL](http://localhost:8089/swagger-ui.html)

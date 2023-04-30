# Spring Security 3.0 with JWT

Only purpose of this application is to provide Spring Security with JWT so there are no proper exception and validations defined.

You can build the project in any ide (preferred intelliJ)

For the first user, run the script in the data.sql file, and then you can create multiple users.

### Running with Docker

First of all install and run the docker.<br>
Run the Dockerfile **docker build -t spring-security-jwt-0.0.1.war .**<br>
to run a container using **docker run --name spring-security-jwt -p 9100:9090 -e spring.datasource.username=postgres -e spring.datasource.password=root -e spring.datasource.url=jdbc:postgresql://192.168.100.21:5432/user-mgmt spring-security-jwt-0.0.1.war**<br>
since this application uses database, I have added the values for db in the environment variables to run, you can skip this if you don't need.<br>

you can directly pull and run the image **kanwaljeet13/spring-security-jwt**<br>
command: **docker run --name spring-security-jwt -p 9100:9090 -e spring.datasource.username=postgres -e spring.datasource.password=root -e spring.datasource.url=jdbc:postgresql://192.168.100.21:5432/user-mgmt kanwaljeet13/spring-security-jwt**<br>
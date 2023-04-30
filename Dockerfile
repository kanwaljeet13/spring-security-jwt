FROM openjdk:17-oracle
# Copy the Spring Boot executable WAR file to the container
COPY target/spring-security-jwt-0.0.1.war spring-security-jwt-0.0.1.war

# Expose the default Spring Boot port
EXPOSE 9090

# Set the entrypoint to run the Spring Boot executable WAR file
ENTRYPOINT ["java", "-jar", "spring-security-jwt-0.0.1.war"]
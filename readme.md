### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8080/swagger-ui.html
- H2 UI : http://localhost:8080/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Modifications implemented by me
- Use Spring's validation annotations (@Valid, @NotBlank, @NotNull, etc.) and Spring Boot's error handling to validate incoming data and provide meaningful error responses.
- Implemented custom exception handlers to return consistent error responses (e.g., using @ControllerAdvice and @ExceptionHandler).
- Enable caching using Spring's caching annotations
- Use profiles to manage different configurations for development, testing, and production environments.
- Define response DTOs (Data Transfer Objects) to encapsulate data exchanged between the client and the service.
- Configure logging using Spring Boot's built-in support for various logging frameworks and used Spring Aspect
- Implemented comprehensive test cases using JUnit for rigorous code validation and quality assurance.
- Updated Swagger/OpenAPI documentation
- Include Actuator for monitoring and managing your application.

#### We can implemented
- Might consider OAuth2 or JWT-based authentication, depending on your requirements.
- When requests originate from any web screen, we have the capability to validate both the request headers and session ID in accordance with the specified requirements.

#### Test Report
[Test Report](https://github.com/Atul-Raut/java-challenge/tree/main/TestResult/TestResult.html)

#### Your experience in Java

Please let us know more about your Java experience in a few sentences. For example:

- I boast a solid decade of hands-on experience in Java, encompassing a wide range of projects and challenges.
- My journey with Spring Boot commenced three years ago, during which I've consistently expanded my proficiency in this versatile framework.
- Spring Boot has become an integral part of my toolkit over the past three years, featuring prominently in numerous applications I've contributed to throughout my career.

#### Repository Link

[GitHub Repository Link](https://github.com/Atul-Raut/java-challenge)


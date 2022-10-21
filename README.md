# Spring Boot REST Person Application
## Task:
Create a **Spring Boot Application** with only one **REST** endpoint.
Request to **REST** endpoint returns **JSON** based on id with name, surname, and person's age.
The application includes **RestController**, **Service**, and **Repository**.
The **DB** saves person data: name, surname, and date of birth.
The person's age is calculated inside the service.
The application has to work with any embedded **DB**.
The **integration** and **unit tests** have to be present.
### Comments to the solution:
1. :heavy_check_mark: Be **REST**, not **RPC**.
> "... if the engine of application state (and hence the API) is not being driven by hypertext, then it cannot be **RESTful** and cannot be a **REST API**."

[Roy Fielding](
https://roy.gbiv.com/untangled/2008/rest-apis-must-be-hypertext-driven)

Without including hypermedia in representation, the request will not be a **REST** service, it will be the **RPC** - **Remote Procedure Call**.
So, for being driven by hypertext I also return inside the **JSON** the link for the requested resource.

2. :heavy_check_mark: **Unit tests** and **Integration tests** are not in the same place, but in dedicated. By changing the **pom.xml** (**maven-failsafe-plugin** and **maven-surefire-plugin**) the unit and integration tests are being splitted.

The tests are launched by the following commands:

```sh
#unit tests
mvn test
```

```sh
#integration tests
mvn verify
```


### Mainly used resources:
- [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
- [Split unit and integration tests](https://blog.worldline.tech/2020/04/10/split-unit-and-integration-tests.html)
- [Stack Overflow](https://stackoverflow.com/)

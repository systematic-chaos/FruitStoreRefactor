# FruitStore

## Refactor project for a fruit store

---

Technology stack:

* Maven 3
* JDK 1.8
* Mosquitto (MQTT broker)
* H2 in-memory database
* Spring Boot
* Swagger2 (REST API documentation)

Testing uses Mockito, JUnit and the Spring test framework.

This development features a simple layered architecture composed by three layers, presented in descending order:

* Controllers: Composed by two classes, that handle REST requests and MQTT messages, respectively.
* Services: Its main component is the FruitService which contains the domain logic. It dispatches calls from the controllers layer, performs the corresponding actions for the operation being invoked and relies on the persistence layer to store and retrieve data as required.
* Persistence: Provides operations for storing into and retrieving data from a H2 database, by means of a JPA repository.

To watch the progression in the development process, please take a look into the commit history.

Suggested additional improvements:

* The project could be splitted into three Maven artifacts, one for each layer in the architecture. This would benefit from some advantages:
  * Minimizes the time required for compiling the project since only the artifacts having being modified need to be rebuilt, not the whole codebase comprising the development.
  * Enables a better continuous-integration flow.
  * As long as their public operation interfaces keep consistent, different artifacts can evolve independently and be managed by different developers or even teams.
* The adoption of Gitflow practices in a real project, instead of just a single main branch, would provide a better control of changes over time.


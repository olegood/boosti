# BoosTI: 'Boost' Your Technical Interview

## Description

Pet-project that make **boost** in collecting questions for technical interview and export them in following formats:

- Plain Text (.txt)
- Microsoft Word (.docx)
- Portable Document Format (.pdf)

Sandbox for mastering coding skills, making architectural experiments and place to use various Java ecosystem tools,
frameworks and libraries.

## Tech Stack (major elements)

### Languages

- [Java 17 (_Amazon Corretto_)](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html)
- [Apache Groovy](https://groovy-lang.org/)

### Frameworks

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring HATEOAS](https://spring.io/projects/spring-hateoas)

### Database Engine and Tools

- [H2 Database Engine](https://www.h2database.com/html/main.html)
- [Liquibase](https://liquibase.org/)

### Testing

- [JUnit Jupiter](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [Hamcrest](http://hamcrest.org/)
- [ArchUnit](https://www.archunit.org/)
- [Spock Framework](https://spockframework.org/)
- [REST-assured](https://rest-assured.io/)
- [Selenide](https://selenide.org/)
- [JaCoCo](https://www.eclemma.org/jacoco/)

### Other libraries and tools

- [Project Lombok](https://projectlombok.org/)
- [Model Mapper](http://modelmapper.org/)
- [Apache POI](https://poi.apache.org/)

### Build Tool

- [Apache Maven](https://maven.apache.org/)

### CI/CD

- [GitHub Actions](https://docs.github.com/en/actions)

### UI/UX

- [React](https://reactjs.org/)
- [Node](https://nodejs.org/en/)
- [NPM](https://www.npmjs.com/)
- [Material UI](https://mui.com/)

## How to Build

Run full build lifecycle:

`$ mvn clean install`

Profiles activated by default: `jacoco` (being used as code coverage tool). Full list of profiles could be found
in `<profiles>` section. Commonly used:

- `jacoco` (_default_)
- `failsafe` (integration tests)
- `selenide` (e2e tests)

Examples of profile activation:

`$ mvn clean install -P failsafe`

`$ mvn clean install -P failsafe, !jacoco`

To get full build with no tests being run:

`$ mvn clean install -DskipTests=true`

or simply

`$ mvn clean install -DskipTests`

## How to Contribute

If you are interested and want to contribute please drop me an [email](mailto:oleg.anastassov@gmail.com). Any ideas are welcome.

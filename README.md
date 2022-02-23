# BoosTI: 'Boost' Your Technical Interview

Pet-project that make **boost** in collecting questions for technical interview and export them in popular formats (
Text, Microsoft Word, PDF etc).

Sandbox for coding, architectural experiments and different Java ecosystem tools, frameworks and libraries usage.

## Tech Stack

List might have some missed items:

- Apache Maven
- Java 17 LTS
- Spring Boot
- Lombok
- Model Mapper
- Liquibase
- Apache POI
- ReactJS
- JUnit 5 + Mockito
- ArchTest

## How to build

In order to make a full build:

`$ mvn clean install`

Profiles activated by default: `jacoco` (being used as code coverage tool). Full list of profiles could be found
in `<profiles>` section. Commonly used:

- `jacoco` (active by default)
- `failsafe` (integration tests)
- `selenide` (e2e tests)

Examples of profile activation:

`$ mvn clean install -P failsafe`

`$ mvn clean install -P failsafe, !jacoco`

To get full build with no tests being run:

`$ mvn clean install -DskipTests=true`

or simply

`$ mvn clean install -DskipTests`

## Contribution

If you are interested and want to contribute please drop me an email.

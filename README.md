# Calculator

Calculator project made for Intive Patronage program (2021 edition).

## Technologies
Java 11 + Spring Boot, Maven, H2

## Installation

Run .jar file from /target folder with command:

```bash
java -jar calc_intive-1.0-SNAPSHOT.jar
```

or run with Maven:
```bash
mvn spring-boot:run
```


Server starts at:

```bash
localhost:8080/api
```

For possible endpoints go to:

```bash
localhost:8080/swagger-ui.html
```

Application can be started as a console app as well, please refer to Main.class in project structure. 


## Usage

Application works with:
1. Numbers (e.g. 1, -2, .3, 0.4 etc)
2. Vectors (e.g. [2,3,.4])
3. Matrix (e.g. [[2,3.4][3,2,4]]

Maximum lengths for vectors and matrix are defined in Configuration.class file from configuration package.
Depending on given values, you can make different calculations, you can check them all typing endpoint /possibleCalculations.

## Database

Application saves results:
- files - you can define default destination and file name in Configuration.class file
- H2 database - you can access H2 console on http://localhost:8080/h2-console, username: sa, no password


## Author
Paweł Brzeziński

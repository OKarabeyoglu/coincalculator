# coincalculator

## Prerequisites

IDE, Git, Java8, PostgreSQL, Docker

## Run the project:

- Clone the project
- Build the **maven** project

```
mvn clean install
```

- Configure datasource and jpa configurations in application.properties

```
  spring.datasource.username=postgres
  spring.datasource.password=postgres
  spring.datasource.url=jdbc:postgresql://localhost:5432/coin
```

- Create **coin** database in **PostgreSQL**
- Run the application

```
http://localhost:8080/swagger-ui.html
```

### with Docker

- Clone the project
- Build the **maven** project
- Run commands below:

```
mvn clean install
```

```
docker-compose up 
```

Then,

```
http://localhost:8080/swagger-ui.html
```







 

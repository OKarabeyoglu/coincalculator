# coincalculator

## Run the project:

- Clone the project
- Build the **maven** project
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

- Build the project 
- Run commands below:
```
maven clean install
```
```
docker-compose up 
```







 

Hoover
============

## Introduction
It is an implementation for yoti-java task.

## Stack
- Java 11
- Spring Boot 2.2.4

## Execution
Move to root package

Execute there
- ```./mvnw -pl infrastructure -am spring-boot:run```
<br /> or
- ```./mvnw.cmd -pl infrastructure -am spring-boot:run```

according to your system.

## Testing
### Swagger
You can use Swagger for testing API.
default url will be - http://localhost:8080/swagger-ui.html#

### Example
Payload looks like:
```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```
Also, it's possible from command line
```
curl -X POST "http://localhost:8080/hoover/v1/clean" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"roomSize\" : [5, 5], \"coords\" : [0, 0], \"patches\" : [ [1, 0], [2, 2], [2, 3] ], \"instructions\" : \"EEEEEENNNNNN\"}"
```

### Output
Service output should be returned as a json payload.

Example:

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```

Author : pmrushi@gmail.com

# spring-gemfire-api

POST - http://localhost:8080/customerObj

Save Customers data

Body 

```
{
    "firstname": "rushi2",
    "lastname": "pamu2",
    "age": 267
}
```

GET - http://localhost:8080/all 

Return all customerObjs in json

```
[
    {
        firstname: "rushi1",
        lastname: "pamu1",
        age: 22
    }
]
```

GET - http://localhost:8080/list

Shows all customerObj in table.

```

./gradlew sonarqube \
  -Dsonar.projectKey=spring-gemfire-api \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=1f1597c5404fff725bc28ecf2510a7bd19233a40
  
```
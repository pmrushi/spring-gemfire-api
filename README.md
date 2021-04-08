# spring-gemfire-api

POST - http://localhost:8080/customer

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

Return all customers in json

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

Shows all customer in table.

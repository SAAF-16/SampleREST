# SampleREST

Implementation of a REST-Service with Spring Boot. It can handle:

- Get single customer by id
- Get list of all customers
- Create a Customer
- Update a Customer
- Delete a Customer

Persistence is handled via JPA and H2 in memory db.

### Examples

POST: http://localhost:8080/api/customer \
{
"name": "test",
"email": "test@hotmail.com"
}

DELETE: http://localhost:8080/api/customer/3

GET: http://localhost:8080/api/customer/2

PUT: http://localhost:8080/api/customer/2?name=test

GET+HTML : http://localhost:8080/api/customer/html/1
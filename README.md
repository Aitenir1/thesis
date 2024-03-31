# Diploma Thesis Repository

It is a web application that serves as a repository for storing and managing students' theses. The project was created as a midterm assignment for the Java course.
* [SRS](https://docs.google.com/document/d/11le1YuR-z26Uhj9YgPK20xqmQBXzjNiuOT8vJiIYCfg/edit?usp=sharing)
## Features

* Upload Thesis Works: Authenticated users can upload their diploma thesis works to the repository.
* Browse and Search: Users can browse through the existing thesis works and search for specific topics or authors.
* Download: Users can download thesis works of interest for reading or reference.
* Integration with MinIO: Diploma thesis works are stored and served using MinIO, an object storage server.

## Technologies Used

* Spring Boot: Backend framework for building RESTful APIs and managing application logic.
* Hibernate: Object-relational mapping library for interacting with the database.
* PostgreSQL: Database management system for storing user data and thesis works.
* MinIO: Object storage server for storing diploma thesis works securely.
* Swagger UI: API documentation tool for exploring and testing RESTful APIs.
* JUnit: Testing framework for unit and integration tests.

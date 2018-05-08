# Core Spring

[![Build Status](https://travis-ci.org/zuzhi/core-spring.svg?branch=master)](https://travis-ci.org/zuzhi/core-spring)
[![License](https://img.shields.io/badge/license-mit-blue.svg)](https://github.com/zuzhi/core-spring/blob/master/LICENSE)

This project is an example project showing how to build a Spring Boot App with Spring related projects, such as Spring Boot, Spring Security, Spring Data JPA...

## Getting Started

### Running the app

```bash
# Clone the project
$ git clone https://github.com/zuzhi/core-spring.git
# Change directory
$ cd core-spring
# Run gradle `bootRun` task
$ gradle bootRun
```

### Endpoints

#### Hello, World

* `/`: Go to http://localhost:8080/, use `user` as username, `pass` as password, see `SecurityConfig.java`

#### Actuator

* `/actuator`
    * `/actuator/health`
    * `/actuator/info`

#### H2 console

* `/h2-console`: h2 console

#### APIs

`/api/v1`

* `/books`
    * `GET`: get all books
    * `POST`: create a new book
* `/books/{bookId}`
    * `GET`: get a book by id
    * `PUT`: update a book by id, id is needed in both request body and request path
    * `PATCH`: update a book by id, id is not needed in request body
    * `DELETE`: delete a book by id


## Deploy

[Deploying Gradle Apps on Heroku](https://devcenter.heroku.com/articles/deploying-gradle-apps-on-heroku)
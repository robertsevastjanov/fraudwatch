# FraudWatch

A full-stack transaction screening demo built with Java, Spring Boot, Angular and TypeScript.

The application evaluates synthetic financial transactions using a simple rule-based screening mechanism.

## Tech Stack

- Backend: Java 21, Spring Boot 4, REST API, Maven
- Frontend: Angular 22, TypeScript, HTML, CSS
- Testing: Spring Boot integration smoke test, manual API testing with cURL
- CI/CD: Jenkins pipeline configuration
- Version Control: Git, GitHub

## Features

- Submit a transaction with sender, receiver and amount
- Classify transactions as CLEAR or FLAGGED
- Flag transactions exceeding EUR 3,000
- Reject non-positive transaction amounts
- Display screening results in the Angular dashboard

The EUR 3,000 threshold is illustrative and does not represent a real banking fraud detection rule.

## Architecture

Angular frontend
    |
    | HTTP / JSON
    v
Spring Boot REST API
    |
    v
Rule-based transaction screening

## Run Backend

Requirements: Java 21

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.autoconfigure.exclude=org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration,org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration"

```

Backend runs at http://localhost:8080

## Run Frontend

Requirements: Node.js 24 and npm.

Open a second terminal and run:

```bash
cd frontend
npm ci
npx ng serve --proxy-config proxy.conf.json
```

Open http://localhost:4200 to access the FraudWatch dashboard.

## API Example

**Endpoint:** `POST /api/transactions/check`

**Request:**

```json
{
  "sender": "Alice",
  "receiver": "Bob",
  "amount": 4500
}
```

**Response:**

```json
{
  "status": "FLAGGED",
  "reason": "Amount exceeds 3000 EUR threshold"
}
```

## CI/CD

The Jenkinsfile defines two stages:

1. Backend — Maven build and tests.
2. Frontend — npm installation and Angular production build.

The pipeline configuration has been created but has not yet been executed in Jenkins.

## Automated Tests

The backend includes six automated tests using JUnit 5 and Spring Boot,
covering transaction screening rules and HTTP integration.

Run tests with:

    cd backend
    ./mvnw test

## Reactive API

POST /api/transactions/reactive/check

This endpoint uses Project Reactor Mono to return screening results.
The application runs on Spring MVC with Reactor integration,
not a fully reactive WebFlux server.

## Roadmap

- PostgreSQL persistence
- SOAP integration example
- OpenShift deployment manifests

## Disclaimer

This is an educational portfolio project using synthetic data only.
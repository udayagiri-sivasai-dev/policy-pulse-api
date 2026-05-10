# Policy Pulse API

Policy Pulse API is a Spring Boot REST backend for managing insurance policy records and policy documents. It supports policy CRUD operations, pagination, status-based search, AWS S3 document upload/download, and Kafka event publishing after a document is uploaded.

## Features

- Create, read, update, and delete policy records
- Paginated policy listing
- Search policies by status
- Upload policy documents to AWS S3
- Download policy documents from AWS S3
- Store only the S3 document key in the database
- Publish a Kafka event when a policy document is uploaded
- Validate request data using Jakarta Bean Validation

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- AWS S3 SDK
- Apache Kafka
- Maven
- Flyway

## Project Structure

```text
src/main/java/com/policypulse/api
├── Policy.java
├── PolicyController.java
├── PolicyRepository.java
├── PolicyService.java
├── S3Config.java
├── S3Service.java
├── PolicyDocumentUploadedEvent.java
└── PolicyKafkaProducer.java

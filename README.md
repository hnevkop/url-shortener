# URL Shortener

The URL Shortener is a simple project that demonstrates a typical URL shortening service like Bit.ly or TinyURL.

## Key Features

- **URL Shortening**: Convert long URLs to short and easy-to-remember formats.
- **Highly Scalable**: Optimized for high load and standalone usage.

## Back-end

Built using Spring Boot and Java 17, the backend provides an API for URL shortening which is used by the frontend.
Shortned URLs are stored in an in-memory H2 database as follows:

* The base 32 system means there are 32 possible characters (2^5, since 32 is a power of 2).
* Since 10 characters are created, this results in 32^10, or 1,099,511,627,776 (about 1.1 trillion) possible combinations.

Application has Swagger on a default path /swagger-ui/index.html

## Front-end

The frontend is built using React. In module directory: /frontend 
It provides a user-friendly interface that interacts with the backend and displays the shortened URLs.

# Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

- JDK 17
- Node.js v14 (for frontend)

## Installation

1. Clone the repo
2. Run the backend server (from the backend directory)./gradlew bootRun
   ./gradlew bootRun
3. Run the frontend. NOTE: it uses the default port 3000. In order to install NPM packages (from the frontend directory) and start
   npm install
   npm start
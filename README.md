# **Email Authentication Service API**

The **Email Authentication Service API** is a secure microservice that handles user authentication and management as part of a distributed system. It supports operations such as user registration, login, and JWT token generation for secure communication between microservices. Designed with scalability and modularity in mind, it serves as a critical foundation for managing access control and identity in the ecosystem.

## **Key Features**

### **Authentication & Authorization**

- **User Registration**: Safely store user information (encrypted) while enforcing email uniqueness.
- **User Login**: Validate credentials and provide a secure, signed JWT token for subsequent API interactions.
- **JWT Token Management**: Ensure stateless authentication via signed JSON Web Tokens with expiration.

### **Security**

- **Password Encryption**: Utilizes **`BCryptPasswordEncoder`** for hashing sensitive user data.
- **Spring Security Integration**: Handles token validation, authentication filters, and role-based access.
- **Input Validation**: Validates incoming requests using Jakarta Validation to mitigate malformed inputs.

### **Modularity and Extensibility**

- Clean separation between layers for ease of maintenance and extension.
- Designed to support additional features like password recovery or OAuth integrations.

---

## **Project Structure**

The service is organized as follows:

```graphql
email-authentication-service-api/
│
├── common/
│   ├── constant/              # Centralized constants (e.g., API paths, roles).
│   ├── dto/                   # Request and response DTOs (e.g., UserRequestDTO, LoginResponseDTO).
│   └── entity/                # JPA entity classes (e.g., UserModel).
│
├── config/
│   ├── SecurityConfig.java    # Configures Spring Security for authentication and role-based access.
│   ├── JwtConfig.java         # Manages JWT token properties and validation logic.
│   └── ValidationConfig.java  # Jakarta Validation integration.
│
├── controller/
│   ├── AuthApi.java           # Defines API endpoints for authentication.
│   └── AuthController.java    # Implements authentication-related APIs.
│
├── repository/
│   └── UserRepository.java    # JPA repository for UserModel persistence.
│
├── service/
│   ├── AuthService.java       # Interface defining core authentication methods.
│   └── impl/
│       └── AuthServiceImpl.java # Implements user authentication and token generation logic.
│
├── resources/
│   └── application.yaml       # Configuration file (DB, JWT, etc.).
└── tests/                     # Unit and integration tests for the service.

```

---

## **Configuration**

### **`application.yaml`**

This microservice uses a YAML-based configuration for ease of setup and environment-specific overrides.

```yaml
server:
  port: 8081
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/email-db
    username: postgres
    password: 1234
  jpa:
    hibernate:
      ddl-auto: update
jwt:
  secret: dbgljbvbaoebvbsZkvbpsabvpibdclkvbdspifvahbcdisvfwvfouyewvbalfibeslfvafbcldasbpfiewaubfbeluafbeafbkjlsbafiaew
springdoc:
  api-docs:
    path: /v1/api-docs
  swagger-ui:
    path: /swagger-ui.html

```

> [!Note]
> Make sure to replace the database URL, username, and password with your actual configuration and AWS Cloud configuration.

### **Key Configuration Details**

- **Port Configuration**: The microservice runs on port **`8081`**.
- **Database**: Connects to a PostgreSQL database (**`email-db`**) using the provided username (**`postgres`**) and password (**`1234`**).
- **JWT Secret**: Uses a secure secret key for signing and validating JWTs.
- **Swagger UI**: OpenAPI 3 documentation is accessible at **`/swagger-ui.html`**.

## **Interaction with Other Microservices**

### **Inputs:**

- **User Registration/Authentication**: Direct API calls from other services or clients for user onboarding or login.
- **JWT Token Validation**: Downstream services validate tokens issued by this service for secure inter-service communication.

### **Outputs:**

- **JWT Tokens**: Signed tokens passed to clients and other microservices for authentication.

---

## **Configuration**

### **Database Configuration**

Stores user information securely in a PostgreSQL database.

```yaml
spring:
  datasource:
    url: jdbc:postgresql://<DB_HOST>:<DB_PORT>/<DB_NAME>
    username: <DB_USER>
    password: <DB_PASSWORD>
  jpa:
    hibernate:
      ddl-auto: update
```

### **JWT Configuration**

Handles token generation and validation for secure API interactions.

```yaml
jwt:
  secret: your-jwt-secret
  expiration: 86400  # Token expiry in seconds (e.g., 24 hours)
```

---

## **API Endpoints**

### **Base Path:**

**`/v1/auth`**

### **User Registration**

- **POST** **`/register`**
- **Description**: Registers a new user.
- **Request Body**:

    ```json
    {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john.doe@example.com",
      "password": "securePassword123"
    }
    ```

- **Response**:

    ```json
    {
      "message": "User successfully registered",
      "accessToken": "jwt-token-string"
    }
    ```


### **User Login**

- **POST** **`/login`**
- **Description**: Authenticates a user and provides a JWT token.
- **Request Body**:

    ```json
    {
      "email": "john.doe@example.com",
      "password": "securePassword123"
    }
    ```

- **Response**:

    ```json
    {
      "accessToken": "jwt-token-string",
      "expiration": "2024-11-15T12:00:00Z"
    }
    ```


### **Token Validation (Optional for Clients)**

- **GET** **`/validate`**
- **Description**: Validates an existing JWT token.

---

## **How Security is Implemented**

- **Authentication Filter**: Uses **`JwtAuthFilter`** to intercept and validate incoming requests.
- **Role-Based Access**: Grants permissions dynamically based on user roles.
- **Stateless Design**: Relies solely on JWT tokens without persisting session data, ensuring scalability.

---

## **Testing**

### **Unit Tests**

Run the following to execute all tests:

```bash
mvn test
```

### **Manual Testing**

- **User Registration**:Use Postman or cURL to call **`/register`** with sample user data.
- **Login**:Authenticate the registered user using **`/login`** and verify the returned JWT.
- **Token Validation**:Test token validation endpoint using the received JWT.

---

## **How to Run the Service**

### **Prerequisites**

- **PostgreSQL Database**: Ensure the database is set up and accessible.
- **Java 17**: Required to run the application.
- **Maven**: For building the project.

### **Steps**

1. Clone the repository.
2. Configure the **`application.yaml`** file with your environment details.
3. Build and run the application:

    ```bash
    ./mvnw spring-boot:run
    ```


---

## Related Microservices

The system consists of multiple microservices that work together to provide comprehensive functionality. Below is a list of all the microservices in the system, with links to their respective repositories:

- [**users-service-api**](https://github.com/juansebstt/users-service-api): Handles user management, including registration, profile updates, and account data.
- [**email-kafka-microservice**](https://github.com/juansebstt/email-kafka-microservice): Manages asynchronous email event processing using Kafka for reliable messaging.
- [**notifications-microservice-api**](https://github.com/juansebstt/notifications-microservice-api): Sends notifications based on triggered events from other services.
- [**email-authentication-service-api**](https://github.com/juansebstt/email-authentication-service-api): Manages email-based authentication and verification processes.
- [**email-api-gateway**](https://github.com/juansebstt/email-api-gateway): Serves as the entry point for routing requests to various microservices.
- [**letter-service-api**](https://github.com/juansebstt/letter-service-api): Manages letters, including creation, storage, and retrieval.
- [**packages-service-api**](https://github.com/juansebstt/packages-service-api): Manages package-related operations, including tracking and status updates.


## **Future Enhancements**

- Add password recovery functionality with email notifications.
- Support OAuth 2.0 and social login integration.
- Implement a user account locking mechanism for failed login attempts.
# Credit Card Management API

This project is a **REST API** for managing customers and credit cards. It is built with **Spring Boot**, uses Hibernate as the ORM, and PostgreSQL as the database. The project includes configurations for both **local execution** and **Docker deployment**.

---

## Features
- Manage customers (create, update, delete, retrieve).
- Manage credit cards associated with customers.
- Built with clean RESTful API architecture.
- Database integration with PostgreSQL.

---

## Prerequisites
Ensure you have the following installed:
- **Java 17** ([Download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- **Maven** ([Download](https://maven.apache.org/download.cgi))
- **PostgreSQL** ([Download](https://www.postgresql.org/))
- **Docker** and **Docker Compose** (optional for containerized deployment)

---

## Project Structure
```plaintext
credit-card-api/
├── src/
│   ├── main/
│   │   ├── java/com/creditcard/management/
│   │   │   ├── application/       # Application logic and services
│   │   │   ├── core/              # Core models
│   │   │   ├── infrastructure/    # Controllers and persistence adapters
│   │   └── resources/
│   │       ├── application.properties  # Database configuration
│   └── test/                        # Unit tests
├── Dockerfile                      # Backend Dockerfile
├── docker-compose.yml              # Docker services configuration
└── README.md                       # Project documentation
```

---

## Running the Project Locally

### 1. Set up PostgreSQL
1. Start a PostgreSQL instance.
2. Create a new database named `credit_card_db`.
3. Update `application.properties` with your PostgreSQL configuration:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/credit_card_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

### 2. Build and Run the Application
1. Open a terminal and navigate to the project root directory:
   ```bash
   cd credit-card-api
   ```
2. Use Maven to build and run the project:
   ```bash
   mvn clean package
   java -jar target/*.jar
   ```
3. The application will start on **`http://localhost:8080`**.

---

## API Endpoints

### Customers
| Method | Endpoint                 | Description                 |
|--------|--------------------------|-----------------------------|
| GET    | `/api/customers`         | Retrieve all customers      |
| GET    | `/api/customers/{id}`    | Retrieve a single customer  |
| POST   | `/api/customers`         | Add a new customer          |
| PUT    | `/api/customers/{id}`    | Update customer details     |
| DELETE | `/api/customers/{id}`    | Delete a customer           |

**Example JSON Payload for POST**:
```json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "creditCards": [
        {
            "cardNumber": "1234567890123456",
            "expirationDate": "2025-12-31",
            "cvv": "123",
            "cardType": "Credit",
            "creditLimit": 5000.00,
            "currentBalance": 2000.00
        }
    ]
}
```

### Credit Cards
| Method | Endpoint                      | Description                       |
|--------|-------------------------------|-----------------------------------|
| GET    | `/api/credit-cards`           | Retrieve all credit cards         |
| POST   | `/api/credit-cards`           | Add a new credit card             |
| PUT    | `/api/credit-cards/{id}`      | Update credit card details        |
| DELETE | `/api/credit-cards/{id}`      | Delete a credit card              |

**Example JSON Payload for POST**:
```json
{
    "cardNumber": "1234567890123456",
    "expirationDate": "2025-12-31",
    "cvv": "123",
    "cardType": "Credit",
    "creditLimit": 5000.00,
    "currentBalance": 2000.00,
    "customerId": 1
}
```

---

## Testing
Run the unit tests using Maven:
```bash
mvn test
```

---

## Notes
- **Database Initialization**: Hibernate automatically generates database schemas.
- **API Port**: The application runs on port **8080** by default.

---

## Author
**Steven Rodriguez**

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Acknowledgments
- **Spring Boot** for backend development.
- **PostgreSQL** for database management.
- **Docker** for containerization.


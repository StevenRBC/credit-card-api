# Credit Card Management API

This project is a REST API for managing customers and credit cards. It is built with **Spring Boot**, uses **Hibernate** as the ORM, and **PostgreSQL** as the database. Additionally, it includes configuration for deploying the project using **Docker**.

## Requirements

Before running the project, ensure the following requirements are met:

- **Docker** and **Docker Compose** are installed.
- Port `8080` is available on your machine (you can change it in the `docker-compose.yml` file if needed).

## Configuration

### Environment Variables

The project is pre-configured to work with Docker, but you can customize the following environment variables:

- `SPRING_DATASOURCE_URL`: URL of the PostgreSQL database.
- `SPRING_DATASOURCE_USERNAME`: Database username.
- `SPRING_DATASOURCE_PASSWORD`: Database password.

These variables are defined in the `application.properties` file and the `docker-compose.yml` file.

### `docker-compose.yml` File

The `docker-compose.yml` file includes two services:
- `app`: The service running the API.
- `db`: The service running PostgreSQL.

## Running the Project

### 1. Clone the Repository

```bash
git clone <repository-url>
cd credit-card-api
```

### 2. Build and Run the Project with Docker

Run the following command from the project root:

```bash
docker-compose up --build
```

This will:
1. Build the Docker image for the application.
2. Start the containers for the application and the PostgreSQL database.

### 3. Verify the Project is Running

Access the API at: [http://localhost:8080/api/customers](http://localhost:8080/api/customers)

### 4. Stop the Containers

When you're done using the application, stop and remove the containers with:

```bash
docker-compose down
```

## API Endpoints

### Customers

#### Get All Customers
- **URL:** `/api/customers`
- **Method:** `GET`

#### Create a Customer
- **URL:** `/api/customers`
- **Method:** `POST`
- **Empty JSON Body:**
    ```json
    {
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com"
    }
    ```
- **JSON Body with Credit Cards:**
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

#### Delete a Customer
- **URL:** `/api/customers/{id}`
- **Method:** `DELETE`

### Credit Cards

#### Get All Credit Cards
- **URL:** `/api/credit-cards`
- **Method:** `GET`

#### Create a Credit Card
- **URL:** `/api/credit-cards`
- **Method:** `POST`
- **JSON Body:**
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

#### Delete a Credit Card
- **URL:** `/api/credit-cards/{id}`
- **Method:** `DELETE`

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
│   │       ├── application.properties  # Application configuration
│   └── test/                        # Unit tests
├── Dockerfile                      # Docker image configuration
├── docker-compose.yml              # Docker services configuration
└── README.md                       # Project documentation
```

## Notes

1. **Database:** Database schemas are automatically generated using Hibernate.
2. **Testing:** You can run project tests with:
    ```bash
    mvn test
    ```
3. **Contribution:** If you'd like to contribute to the project, ensure you follow best practices for **Spring Boot** and **Docker**.

That's all! 🚀


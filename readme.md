# Java Unit Testing & Integration Tests 🚀

This document covers Java project testing, including examples of test writing and execution. It includes test scenarios for the User Management Service and general JUnit 5 testing practices.

## 🏗 Java Unit Testing Examples

This section provides various Java unit testing examples and methodologies.

### 📌 JUnit Lifecycle (az.ders.intro)
- `@BeforeAll` / `@AfterAll`: Runs once before/after all tests.
- `@BeforeEach` / `@AfterEach`: Runs before/after each test.

### 📌 Calculator (az.ders.calculator)
- Implements basic arithmetic operations (add, divide, multiply).
- Uses `@ParameterizedTest` with `CsvSource` for testing.
- Handles exceptions for division by zero.

### 📌 FizzBuzz (az.ders.fizzbuzz)
- Implements the FizzBuzz problem:
  - Multiples of 3 → "Fizz"
  - Multiples of 5 → "Buzz"
  - Multiples of both → "FizzBuzz"
- Uses `@CsvFileSource` for parameterized testing with different datasets.

### 📌 TicTacToe (az.ders.tictac)
- Implements a simple Tic-Tac-Toe game with:
  - Board validation
  - Win/draw detection
  - Turn management
- Includes unit tests for:
  - Invalid moves
  - Player turns
  - Win conditions


## 🛠 User Management Service Tests

### 📌 Controller Tests

#### UserControllerTest
- **testGetAllUsers**: Verifies that the API returns a list of users.
- **testGetUserById**: Ensures the correct user is returned when fetching by ID.
- **testCreateUser**: Confirms that a new user is successfully created.
- **testDeleteUser**: Ensures that deleting a user results in a 204 No Content response.

### 📌 Service Tests

#### UserServiceTest
- **testGetAllUsers**: Ensures all users are retrieved from the repository.
- **testGetUserById_UserExists**: Confirms correct user retrieval when the user exists.
- **testGetUserById_UserNotFound**: Checks that an exception is thrown when a user is not found.
- **testCreateUser**: Verifies that a new user is successfully saved.
- **testDeleteUser**: Ensures that user deletion is handled correctly.

### 📌 Integration Tests

#### UserIntegrationTest
- **testGetAllUsers**: Ensures that users can be fetched after creation.
- **testGetUserById**: Verifies the correct retrieval of a newly created user.
- **testCreateUser**: Confirms successful user creation.
- **testDeleteUser**: Ensures that a deleted user cannot be fetched again.

## 🛠 Test Environment & Tools
- **Testcontainers** is used for PostgreSQL.
- **TestcontainersConfiguration** initializes a PostgreSQL container for testing.
- **Spring Boot Test** is used for integration testing.
- **MockMvc** is applied for controller testing.
- **Mockito** is used for service layer testing.
- **JUnit 5** serves as the primary testing framework.


## 🤝 Contributing

Feel free to fork and improve this project!

## 📜 License

MIT License
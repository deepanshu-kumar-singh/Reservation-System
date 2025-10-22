# Travel Booking Management Service - Adapter Module

This `adapter` module is a crucial component of the Travel Booking Management Service. Its primary responsibility is to act as an entry point for external requests, handling the initial processing, validation, and orchestration of flight booking operations. It leverages several design patterns to ensure a robust, maintainable, and extensible architecture.

## 1. Architectural Flow for Flight Booking

The following sequence describes the high-level flow when a flight booking request is received by the `adapter` module:

1.  **Request Reception**: A `POST` request containing `FlightBookingRequest` data is received by the `BookingController`.
2.  **Initial Validation**: The `@Valid` annotation on the `FlightBookingRequest` in the controller triggers standard Bean Validation (JSR-303). If this validation fails, a `MethodArgumentNotValidException` is thrown and caught by the `GlobalExceptionHandler`, returning a `400 Bad Request` to the client with detailed error messages.
3.  **Service Delegation**: If initial validation passes, the `BookingController` delegates the request to the `FlightBookingServiceImpl` (which extends `AbstractBookingService`).
4.  **Template Method Execution**: The `AbstractBookingService`'s `bookFlight` method (the Template Method) orchestrates the following steps:
    a.  **Business Validation**: The `validate` method is called, which in turn uses the injected `BookingValidationStrategy` (e.g., `DefaultBookingValidationStrategy`) to perform business-specific validation. If this validation fails, a `ValidationException` is thrown.
    b.  **Success Handling**: If business validation succeeds, the `onSuccess` method is called. This method uses the injected `BookingSuccessStrategy` (e.g., `SagaStartSuccessStrategy`) to initiate the booking process (e.g., calling an orchestrator service to start a Saga).
    c.  **Error Handling**: If a `ValidationException` is caught during business validation, the `onError` method is called. This method uses the injected `BookingErrorStrategy` (e.g., `DefaultBookingErrorStrategy`) to format and return a structured error response.
5.  **Response to Client**: The `BookingController` receives the `Map<String, String>` response from the service layer and wraps it in a `ResponseEntity`, returning it to the client (typically `202 Accepted` for successful initiation or `202 Accepted` with an error map for business validation failures).

## 2. Design Patterns Employed

This module is a showcase of several well-established design patterns, contributing to its robustness and maintainability:

### 2.1. Template Method Pattern
-   **Description**: Defines the skeleton of an algorithm in a base class, deferring some steps to subclasses.
-   **Usage**: `AbstractBookingService<T>` is the abstract base class. Its `bookFlight(T request)` method is the `final` template method, enforcing the sequence: `validate` -> (`onSuccess` or `onError`). Subclasses (like `FlightBookingServiceImpl`) provide concrete implementations for these abstract steps.
-   **Benefits**: Guarantees a consistent, unchangeable workflow for core operations while allowing flexibility in specific step implementations. Prevents developers from accidentally altering the fundamental process.

### 2.2. Strategy Pattern
-   **Description**: Defines a family of algorithms, encapsulates each one, and makes them interchangeable.
-   **Usage**: `FlightBookingServiceImpl` delegates its `validate`, `onSuccess`, and `onError` operations to separate strategy interfaces (`BookingValidationStrategy`, `BookingSuccessStrategy`, `BookingErrorStrategy`) and their concrete implementations (e.g., `DefaultBookingValidationStrategy`, `SagaStartSuccessStrategy`, `DefaultBookingErrorStrategy`).
-   **Benefits**: Allows the behavior of each step to be changed or extended independently without modifying the `FlightBookingServiceImpl`. Enhances modularity, testability, and extensibility.

### 2.3. Global Exception Handling (Architectural Pattern)
-   **Description**: Centralized mechanism to catch and handle exceptions across the entire application.
-   **Usage**: Implemented via `@ControllerAdvice` in `GlobalExceptionHandler`. It specifically intercepts `MethodArgumentNotValidException` (from `@Valid` annotations) to return structured `400 Bad Request` responses.
-   **Benefits**: Provides a consistent error response format to clients, prevents boilerplate `try-catch` blocks in controllers, and improves the overall robustness of the API.

## 3. Feature Guidance and Extensibility

This design provides clear pathways for future feature development and modifications:

### 3.1. Adding New Booking Types (e.g., Hotel, Car Rental)
-   **Guidance**:
    1.  Create new DTOs (e.g., `HotelBookingRequest`, `CarRentalRequest`).
    2.  Create new concrete service implementations extending `AbstractBookingService<NewRequestType>` (e.g., `HotelBookingServiceImpl extends AbstractBookingService<HotelBookingRequest>`).
    3.  Implement the `validate`, `onSuccess`, and `onError` methods in the new service, potentially reusing existing strategies or creating new ones specific to the booking type.
    4.  Create new controllers (e.g., `HotelBookingController`) to expose the new booking functionality.

### 3.2. Changing Validation Logic
-   **Guidance**:
    1.  Modify `DefaultBookingValidationStrategy` for changes to the current flight booking validation.
    2.  To introduce entirely different validation rules (e.g., for premium users), create a new implementation of `BookingValidationStrategy` (e.g., `PremiumUserBookingValidationStrategy`).
    3.  Inject the desired validation strategy into `FlightBookingServiceImpl` (this might require a factory or conditional bean creation if multiple strategies are needed at runtime).

### 3.3. Altering Success Handling (e.g., Different Orchestrator, Event Bus)
-   **Guidance**:
    1.  Modify `SagaStartSuccessStrategy` to change how the current flight booking saga is initiated.
    2.  To use a different mechanism for success (e.g., publishing to a Kafka topic instead of a REST call), create a new implementation of `BookingSuccessStrategy` (e.g., `KafkaBookingSuccessStrategy`).
    3.  Inject the new success strategy into `FlightBookingServiceImpl`.

### 3.4. Customizing Error Responses
-   **Guidance**:
    1.  Modify `DefaultBookingErrorStrategy` to change the format or content of error messages for business validation failures.
    2.  For different types of errors (e.g., external service errors), you might introduce new `BookingErrorStrategy` implementations or extend the `GlobalExceptionHandler` to catch other specific exceptions.

### 3.5. Enhancing Cross-Cutting Concerns (Logging, Monitoring)
-   **Guidance**:
    1.  **Logging**: Use Spring's AOP (Aspect-Oriented Programming) to add logging aspects around service methods or controller endpoints without modifying the core business logic.
    2.  **Monitoring**: Integrate with monitoring tools by adding metrics (e.g., using Micrometer) to key operations within the service or strategy classes.

## 4. Getting Started

1.  **Prerequisites**: Java 17+, Maven, Docker (for orchestrator/other services).
2.  **Build**: `mvn clean install`
3.  **Run**: `java -jar target/adapter-0.0.1-SNAPSHOT.jar` (or run from your IDE).
4.  **Configuration**: Ensure `application.yml` has the correct `orchestrator.service.url` and database settings.

---

This `adapter` module exemplifies a well-structured Spring Boot application, ready for scalable and maintainable development.

# Booking Management Adapter

## 1. Overview

This adapter service acts as a unified entry point for a variety of booking and customer-related queries. It is designed to receive simple string-based requests, intelligently parse and validate them, and route them to the appropriate downstream microservice. 

The core feature of this adapter is its ability to interpret different request formats for different flows, such as flight booking, availability checks, and customer data lookups, all through a single, consistent API endpoint.

## 2. Architecture & Design Patterns

This service is built on a highly modular and decoupled architecture, leveraging several key design patterns:

- **Chain of Responsibility**: The core routing logic is built on a chain of handlers (`BookingFlowHandler`). Each handler is responsible for recognizing a specific request format. If a handler cannot process a request, it passes it to the next handler in the chain. This makes the system incredibly easy to extend with new request types.

- **Composite Pattern**: The validation layer uses a composite structure. Main validators (e.g., `FlightBookingValidator`) are composed of smaller, single-purpose field validators (e.g., `AirlineCodeValidator`, `DateValidator`). This makes validation logic granular, reusable, and easy to maintain.

- **Factory Pattern**: A `HandlerChainFactory` is used to encapsulate the complex process of building the handler chain, including the injection of all necessary services, parsers, and validators. This simplifies the router and centralizes dependency management.

- **Strategy Pattern (via Spring Profiles)**: The application uses interfaces for orchestration (`FlightBookingOrchestrator`) and has two sets of implementations ("real" and "mock"). Spring Profiles are used as a strategy selector to determine which implementation to activate at runtime, allowing for easy switching between live network calls and mock development data.

- **Externalized Configuration**: All user-facing error messages are managed in a dedicated `errorMapping.yml` file and loaded using Spring's `@ConfigurationProperties`. This allows for error messages to be changed without recompiling the code.

## 3. Configuration

### 3.1. Error Messages

All business error messages are defined in `src/main/resources/errorMapping.yml`. The application maps an internal error code to a user-facing string.

```yaml
error:
  messages:
    ERR-001: "The request format is invalid. Please check the documentation."
    ERR-002: "The provided data is not valid."
```

To add a new error, simply add a new key-value pair to this file and reference the new key in the application code.

### 3.2. Mock vs. Real Services (Spring Profiles)

To facilitate development without needing live downstream services, the application includes a `mock` profile.

- **To activate mock services**: Add the following to your `src/main/resources/application.yml` file. The application will return hardcoded mock data for all orchestration calls.

```yaml
spring:
  profiles:
    active: mock
```

- **To activate real services**: Comment out or remove the `active: mock` line. The application will attempt to make live HTTP calls to the configured downstream service URLs.

## 4. API Usage

All interactions are handled through a single endpoint.

- **Endpoint**: `POST /api/v1/bookings`
- **Headers**: `Content-Type: application/json`
- **Success Status Code**: `200 OK` (Note: The HTTP status is always 200 OK. The success or failure of the business operation is indicated within the response body).

### 4.1. Request Body Format

```json
{
    "query": "<Your Request String>"
}
```

### 4.2. Request Examples

#### Flight Booking
- **Query**: `OAAY13SEPDFWSEANN1`
- **Response (Mock)**: `Booking Confirmed: {booking-id}{AA}{14A}{CONFIRMED}`

#### Availability Check
- **Query**: `START 23SEPDFWSEA`
- **Response (Mock)**: `Mock Response: 3 flights available from DFW to SEA on 23SEP`

#### Customer Name Query
- **Query**: `my name is John Doe`
- **Response (Mock)**: `Mock Response: Customer profile found for name: John Doe`

#### Customer Phone Number Query
- **Query**: `1234567890`
- **Response (Mock)**: `Mock Response: Customer profile found for phone number: 1234567890`

### 4.3. Error Response Examples

#### Invalid Format Error
- **Query**: `this is not a valid query`
- **Response**: `The request format is invalid. Please check the documentation.`

#### Validation Error
- **Query**: `OAAY13SEP` (An incomplete flight booking string)
- **Response**: `The provided data is not valid.`

## 5. Build and Run

This is a standard Spring Boot application built with Maven.

1.  **Build the application**:
    ```bash
    mvn clean install
    ```

2.  **Run the application**:
    ```bash
    java -jar target/adapter-0.0.1-SNAPSHOT.jar
    ```

The service will start on the configured port (default is 8080).

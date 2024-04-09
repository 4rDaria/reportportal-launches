# reportportal-launches
Test Automation Framework (TAF) for performing API and UI tests for launches functionality of ReportPortal

# Features
### API Testing
Includes test cases to validate the API endpoints related to launches functionality.

### UI Testing
Includes test cases to validate the user interface of the launches functionality.

### Integration with ReportPortal
Test results are reported to ReportPortal, providing centralized test execution and reporting.

### Modular Structure
The framework is organized into core, business, and tests layers, allowing for easy maintenance and scalability.

### Customizable Configuration
Configuration settings such as endpoints, credentials, and test data can be easily customized.

### Logging and Reporting
Integrated logging and reporting mechanisms provide detailed information about test execution and results.

## Getting Started
### Clone this repository
git clone https://github.com/your-username/reportportal-launches.git

### Navigate to the project directory
cd reportportal-launches

### Set environment variable
set env in PropertyReader file (private static final String environment) as "web" or "local"

### Run the tests
To run all test:
mvn clean test

To run some test:
mvn test -Dtest=<testName>
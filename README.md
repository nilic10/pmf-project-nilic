# PMF Automated Tests Project

This project contains automated tests for UI (Selenium) and REST API, developed as part of the PMF project. The project is based on Java and utilizes modern tools and libraries for software quality assurance.

## 🚀 Technologies and Tools

- **Java 17**: Core programming language.
- **Maven**: Project and dependency management tool.
- **JUnit 5**: Framework for writing and running tests.
- **Selenium WebDriver**: For automating UI tests on web browsers.
- **Spring RestTemplate**: For testing REST APIs.
- **Allure Report**: For generating detailed and visually rich testing reports.
- **Lombok**: For reducing boilerplate code (getters, setters, builders).
- **WebDriverManager**: For automatic browser driver management.

## 📁 Project Structure

The project is divided into several key packages:

### 🌐 Selenium (UI Tests) - `com.example.selenium`
- **`base`**: Contains `BasePage` (common methods for Page Objects) and `BaseTest` (driver setup/teardown, screenshot on failure).
- **`pages`**: Implementation of the Page Object Model (POM). Each application page has its own class (e.g., `LoginPage`, `ArticlesPage`, `AddArticlePage`).
- **`utils`**: Utility classes like `DriverFactory` for initializing different browsers.

### 🔌 REST API Tests - `com.example.rest`
- **`BaseRest`**: Base class with methods for HTTP requests (GET, POST, PUT, PATCH, DELETE).
- **`RestClient`**: A facade that integrates all specific clients for easier use in tests.
- **`common`**: Specific clients for various resources (`ArticleClient`, `UserClient`, `CommentClient`, `FileClient`).
- **`models`**: POJO classes representing system entities (Article, User, Comment, File).

### 🧪 Tests - `com.example.tests`
- **`selenium`**: UI tests (e.g., `LoginTest`).
- **`rest`**: API tests (e.g., `RestUserTest`).

## 🛠 Installation and Execution

### Prerequisites
- Java 17+
- Maven 3.6+
- Google Chrome or Firefox (locally)

### Compiling the project
```bash
mvn clean compile
```

### Running tests
Run all tests:
```bash
mvn test
```

Run a specific test:
```bash
mvn test -Dtest=LoginTest
```

Run with a specific browser (default is chrome):
```bash
mvn test -Dbrowser=firefox
```

Run with a proxy:
```bash
mvn test -Dproxy=localhost:9999
```

## 📊 Reporting (Allure)

After running the tests, results are located in `target/allure-results`. To generate the report, run:

```bash
mvn allure:report
```

This command will generate a static HTML report (index.html) in the `target/site/allure-maven-plugin` directory.

## 📝 Documentation

All key classes and methods are documented using **Javadoc** in English, facilitating code understanding and further development.

## 🔧 Configuration

Base paths are configured in the code but can be overridden via system properties:
- `baseUrl`: Base URL for the REST API (default: `http://localhost:3000/api`)
- `browser`: Browser for Selenium tests (chrome, firefox)
- `gridUrl`: URL for Selenium Grid if remote drivers are used
- `proxy`: Proxy server address (e.g., `localhost:8080`)
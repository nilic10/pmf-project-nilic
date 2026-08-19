# PMF Automated Tests Project

This project contains automated tests for UI (Selenium) and REST API, developed as part of the PMF project. It is built on Java and uses modern tools and libraries for software quality assurance.

---

## Application Under Test

The application being tested is **🦎 GAD (GUI API Demo)** — an open-source demo application designed for QA learning and practice.

- **Repository**: https://github.com/jaktestowac/gad-gui-api-demo
- **Docker Hub**: https://hub.docker.com/r/jaktestowac/gad
- **Swagger Documentation**: http://localhost:3000/tools/swagger.html

GAD provides a GUI, a REST API, and integrated Swagger documentation. It intentionally contains bugs and design flaws to simulate real-world project challenges.

### Running Locally (Node.js)

Requires Node.js v20 or v22.

```bash
npm install
npm run start
```

The application starts at `http://localhost:3000`.

### Running with Docker

```bash
docker run -p 3000:3000 -d jaktestowac/gad
```

To run a specific version:
```bash
docker run -p 3000:3000 -d jaktestowac/gad:2.5.5
```

### Environment Variables

| Variable | Default | Description |
|---|---|---|
| `PORT` | `3000` | Port the application listens on |
| `DB` | — | Loads an alternate JSON database file |
| `READ_ONLY` | — | Set to `1` to disable POST, PUT, PATCH methods (except login) |

**Example with custom port:**
```bash
# Bash
PORT=3001 npm run start

# PowerShell
$env:PORT=3001; npm run start

# Windows CMD
set PORT=3001 && npm run start
```

---

## Technologies and Frameworks

| Technology | Version | Purpose |
|---|---|---|
| **Java** | 17 | Programming language |
| **Maven** | 3.6+ | Project and dependency management |
| **JUnit 5** | 5.10.0 | Framework for writing and running tests |
| **Selenium WebDriver** | 4.38.0 | Browser UI test automation |
| **Spring RestTemplate** | 6.1.1 | HTTP client for REST API tests |
| **Apache HttpClient 5** | 5.3.1 | HTTP engine for RestTemplate (SSL, proxy) |
| **Jackson Databind** | 2.15.2 | JSON ↔ POJO serialization/deserialization |
| **Allure Report** | 2.24.0 | Test report generation |
| **Lombok** | 1.18.30 | Boilerplate reduction (getters, setters, builders) |
| **BrowserMob Proxy** | 2.1.5 | Network traffic capture (HAR files) |
| **AspectJ Weaver** | 1.9.20.1 | Allure integration with JUnit 5 |

---

## Project Structure

```
src/
├── main/java/com/example/
│   ├── rest/
│   │   ├── BaseRest.java               # Abstract base class with HTTP methods
│   │   ├── RestClient.java             # Facade integrating all resource clients
│   │   ├── common/
│   │   │   ├── articles/
│   │   │   │   ├── ArticleClient.java       # CRUD + assertions for Article
│   │   │   │   └── AllArticlesClient.java   # Fetching the article list
│   │   │   ├── comments/
│   │   │   │   ├── CommentClient.java       # CRUD + assertions for Comment
│   │   │   │   └── AllCommentsClient.java   # Fetching the comment list
│   │   │   ├── users/
│   │   │   │   ├── UserClient.java          # CRUD + assertions for User
│   │   │   │   └── AllUsersClient.java      # Fetching the user list
│   │   │   └── files/
│   │   │       ├── FileClient.java          # CRUD for File
│   │   │       └── AllFilesClient.java      # Fetching the file list
│   │   └── models/
│   │       ├── Article.java            # POJO model for an article
│   │       ├── Comment.java            # POJO model for a comment
│   │       ├── User.java               # POJO model for a user
│   │       ├── File.java               # POJO model for a file
│   │       ├── UserLoginData.java      # Login request model
│   │       └── UserLoginToken.java     # Login response model with token
│   └── selenium/
│       ├── base/
│       │   ├── BasePage.java           # Common logic for all Page Objects
│       │   └── BaseTest.java           # WebDriver setup/teardown, screenshots
│       ├── pages/
│       │   ├── LoginPage.java
│       │   ├── HomePage.java
│       │   ├── ArticlesPage.java
│       │   ├── AddArticlePage.java
│       │   ├── CommentsPage.java
│       │   ├── UsersPage.java
│       │   ├── StatisticsPage.java
│       │   ├── MyProfilePage.java
│       │   └── UploadPage.java
│       └── utils/
│           ├── DriverFactory.java                  # WebDriver initialization per browser
│           └── BrowserMobProxyServiceCreator.java  # BrowserMob Proxy management
└── test/java/com/example/tests/
    ├── rest/
    │   ├── SqlInjectionLoginTest.java  # Security test for the login endpoint
    │   ├── articles/     # REST tests for articles
    │   ├── comments/     # REST tests for comments
    │   ├── users/        # REST tests for users
    │   └── files/        # REST tests for files
    └── selenium/
        ├── articles/     # UI tests for articles
        │   └── XssCreateArticleInjectionTest.java  # Security test for the article form
        ├── comments/     # UI tests for comments
        ├── login/        # UI tests for login/logout
        ├── statistics/   # UI tests for statistics
        └── users/        # UI tests for users
```

---

## REST API Framework

### `BaseRest<T>`

Abstract base class for all REST clients. Configures `RestTemplate` with SSL and proxy support.

| Method | Description |
|---|---|
| `get(endpoint, responseType)` | HTTP GET request |
| `post(endpoint, body, responseType)` | HTTP POST request |
| `put(endpoint, body, responseType)` | HTTP PUT request (full update) |
| `patch(endpoint, body, responseType)` | HTTP PATCH request (partial update) |
| `delete(endpoint)` | HTTP DELETE request |
| `setToken(token)` | Sets the Bearer token in request headers |
| `getData()` / `setData(data)` | Access to the client's data model |
| `getBaseUrl()` | Returns the base URL (from system property or default) |

### `RestClient`

Facade that combines all resource clients into a single interface. Tests extend `RestClient` instead of using individual clients directly.

**Authentication**

| Method | Description |
|---|---|
| `login(email, password)` | Authenticates the user and returns a JWT token |

**Articles**

| Method | Description |
|---|---|
| `findArticleById(id)` | Fetches an article by ID → `ArticleClient` |
| `getAllArticles()` | Fetches all articles → `AllArticlesClient` |
| `createArticle(token, article)` | Creates a new article → `ArticleClient` |
| `updateArticle(token, id, article)` | Updates an article (PUT) → `ArticleClient` |
| `patchArticle(token, id, article)` | Partially updates an article (PATCH) → `ArticleClient` |
| `deleteArticle(id)` | Deletes an article |

**Comments**

| Method | Description |
|---|---|
| `findCommentById(id)` | Fetches a comment by ID → `CommentClient` |
| `getAllComments()` | Fetches all comments → `AllCommentsClient` |
| `createComment(token, comment)` | Creates a new comment → `CommentClient` |
| `updateComment(token, id, comment)` | Updates a comment (PUT) → `CommentClient` |
| `patchComment(token, id, comment)` | Partially updates a comment (PATCH) → `CommentClient` |
| `deleteComment(id)` | Deletes a comment |

**Users**

| Method | Description |
|---|---|
| `findUserById(id)` | Fetches a user by ID → `UserClient` |
| `getAllUsers()` | Fetches all users → `AllUsersClient` |
| `createUser(user)` | Creates a new user → `UserClient` |
| `updateUser(id, user)` | Updates a user (PUT) → `UserClient` |
| `patchUser(id, user)` | Partially updates a user (PATCH) → `UserClient` |
| `deleteUser(id)` | Deletes a user |

**Files**

| Method | Description |
|---|---|
| `createFile(token, file)` | Creates a new file entry → `FileClient` |
| `updateFile(token, file)` | Updates a file (PUT) → `FileClient` |
| `patchFile(token, file)` | Partially updates a file (PATCH) → `FileClient` |
| `getUploadedFiles()` | Fetches files uploaded by the authenticated user → `AllFilesClient` |
| `getPublicFiles()` | Fetches publicly available files → `AllFilesClient` |

### Resource Clients — Assertion Methods

Each resource client (`ArticleClient`, `CommentClient`, `UserClient`) exposes fluent assertion methods for verifying response data.

**`UserClient`**

| Method | Description |
|---|---|
| `verifyId(expectedId)` | Asserts the user ID |
| `verifyEmail(expectedEmail)` | Asserts the email address |
| `verifyFirstname(expectedFirstname)` | Asserts the first name |
| `verifyLastname(expectedLastname)` | Asserts the last name |
| `verifyAvatar(expectedAvatar)` | Asserts the avatar URL |

**`ArticleClient`**

| Method | Description |
|---|---|
| `verifyId(expectedId)` | Asserts the article ID |
| `verifyTitle(expectedTitle)` | Asserts the title |
| `verifyBody(expectedBody)` | Asserts the body content |
| `verifyUserId(expectedUserId)` | Asserts the author's user ID |

**`CommentClient`**

| Method | Description |
|---|---|
| `verifyId(expectedId)` | Asserts the comment ID |
| `verifyBody(expectedBody)` | Asserts the comment text |
| `verifyArticleId(expectedArticleId)` | Asserts the associated article ID |
| `verifyUserId(expectedUserId)` | Asserts the author's user ID |

---

## REST API Tests

All REST tests extend `RestClient` and use `@Epic` / `@Feature` Allure annotations.

### Articles

| Test Class | Type | Description |
|---|---|---|
| `CreateArticleTest` | Positive | Creates a user and an article, verifies title, body, and author ID |
| `GetArticleByIdTest` | Positive | Fetches article with ID=1, verifies ID and title |
| `GetNonExistentArticleTest` | Negative | Expects `404 Not Found` for a non-existent ID |
| `FailingArticleTest` | Failing | Fetches a non-existent article without expecting an exception (intentionally fails) |

### Comments

| Test Class | Type | Description |
|---|---|---|
| `CreateCommentTest` | Positive | Creates a user and a comment, verifies body, article ID, and author ID |
| `GetCommentByIdTest` | Positive | Fetches comment with ID=1, verifies ID and body text |
| `DeleteCommentWithoutAuthTest` | Negative | Expects `401 Unauthorized` when deleting without a token |
| `XssCreateCommentInjectionTest` | Security | Creates a comment with an XSS script tag payload, verifies the stored body is sanitized |
| `FailingCommentTest` | Failing | Fetches a non-existent comment without expecting an exception (intentionally fails) |
| `FailingDeleteCommentTest` | Failing | Deletes a comment without a token without expecting an exception (intentionally fails) |

### Users

| Test Class | Type | Description |
|---|---|---|
| `CreateUserTest` | Positive | Creates a user, verifies email, first name, and last name |
| `GetUserByIdTest` | Positive | Fetches user with ID=1, verifies ID and first name |
| `GetAllUsersTest` | Positive | Fetches all users, verifies user data for ID=1 and ID=2 |
| `GetNonExistentUserTest` | Negative | Expects `404 Not Found` for a non-existent ID |
| `DeleteNonExistentUserTest` | Negative | Expects `401 Unauthorized` when deleting without a token |
| `FailingUserTest` | Failing | Creates a user without an email (intentionally fails with a validation error) |

### Files

| Test Class | Type | Description |
|---|---|---|
| `GetPublicFilesTest` | Negative | Expects `404 Not Found` for the public files endpoint |

### Security

| Test Class | Type | Description |
|---|---|---|
| `SqlInjectionLoginTest` | Security | Sends a SQL injection payload (`admin' OR '1'='1`) in the login username, verifies `401 Unauthorized` is returned |

---

## Selenium Framework

### `BaseTest`

Base class for all Selenium tests. Manages the WebDriver lifecycle and screenshot capture on failure.

| Method | Description |
|---|---|
| `createDriver()` | `@BeforeEach` — initializes WebDriver for the specified browser |
| `tearDown()` | `@AfterEach` — quits the browser and stops the proxy |
| `saveScreenshot(name)` | Captures a screenshot and attaches it to the Allure report |
| `saveHar(fileName)` | Captures a HAR file of network traffic and attaches it to the Allure report |
| `newHar()` | Starts a new HAR recording in BrowserMob Proxy |
| `stopBrowserMobProxyService()` | Stops the BrowserMob Proxy service |

### `BasePage`

Base class for all Page Objects. Provides wrapper methods for element interaction and assertions.

**Element Interaction**

| Method | Description |
|---|---|
| `click(locator)` | Waits for the element to be clickable, then clicks it |
| `type(locator, text)` | Waits for visibility, clears the field, and types text |
| `getText(locator)` | Waits for visibility and returns the element's text |
| `isDisplayed(locator)` | Returns `true` if the element is visible |
| `waitForElementToBeVisible(locator)` | Waits for element visibility (timeout: 10s) |
| `waitForElementToBeClickable(locator)` | Waits for element to be clickable (timeout: 10s) |
| `navigateTo(url)` | Navigates to the given URL |

**Assertions**

| Method | Description |
|---|---|
| `verifyText(locator, expectedText)` | Asserts the element's text content |
| `verifyText(locator, expectedText, errorMessage)` | Asserts text with a custom error message |
| `verifyElementDisplayed(locator)` | Asserts that the element is displayed |
| `verifyElementDisplayed(locator, errorMessage)` | Asserts visibility with a custom error message |
| `verifyTitle(expectedTitle)` | Asserts the page title |

### `DriverFactory`

Creates WebDriver instances for the specified browser. Supports local and remote execution.

| Browser Value | Description |
|---|---|
| `chrome` | Local ChromeDriver |
| `firefox` | Local FirefoxDriver |
| `remote-chrome` | RemoteWebDriver (Chrome) via Selenium Grid |
| `remote-firefox` | RemoteWebDriver (Firefox) via Selenium Grid |

### Page Objects

| Class | URL | Key Methods |
|---|---|---|
| `LoginPage` | `/login` | `login(email, password)`, `loginWithError(email, password)`, `verifyLoginPageIsDisplayed()`, `verifyLoginErrorIsDisplayed()` |
| `HomePage` | `/` | `goToArticles()`, `goToMyArticles()`, `goToComments()`, `goToUsers()`, `goToStatistics()`, `goToMyProfile()`, `goToUpload()`, `logout()`, `verifyWelcomeMessage(msg)`, `verifyHomePageIsDisplayed()` |
| `ArticlesPage` | `/articles` | `searchFor(text)`, `addArticle()`, `verifyArticleWithTitleExists(title)`, `verifyArticleCount(n)`, `verifySuccessMessage(msg)`, `verifyArticlesPageIsDisplayed()`, `nextPage()`, `prevPage()` |
| `AddArticlePage` | `/articles/add` | `enterTitle(title)`, `enterContent(content)`, `clickSave()`, `verifySuccessMessage(msg)`, `verifyArticleWithTitleExists(title)` |
| `CommentsPage` | `/comments` | `searchFor(text)`, `verifyCommentsPageIsDisplayed()` |
| `UsersPage` | `/users` | `verifyUsersPageIsDisplayed()` |
| `StatisticsPage` | `/statistics` | `verifyStatisticsPageIsDisplayed()` |
| `MyProfilePage` | `/profile` | `verifyMyProfilePageIsDisplayed()` |
| `UploadPage` | `/upload` | `verifyUploadPageIsDisplayed()` |

---

## Selenium Tests

All Selenium tests extend `BaseTest`. Test users are created via the REST API in the `@BeforeEach` method to keep tests independent of each other.

### Articles

| Test Class | Description |
|---|---|
| `ViewArticlesTest` | Logs in and verifies that the articles page is displayed |
| `SearchArticleTest` | Creates an article via API, searches for it in the UI, and verifies the search result |
| `CreateArticleTest` | Creates an article via the UI form, verifies the success message and that it appears in the list |
| `CheckMyArticlesTest` | Creates an article via API, navigates to "My Articles", and verifies the article is shown with the correct count |
| `XssCreateArticleInjectionTest` | Creates an article with an XSS script tag payload in the title via the UI form, verifies the payload is not rendered as executable HTML |

### Comments

| Test Class | Description |
|---|---|
| `ViewCommentsTest` | Logs in and verifies that the comments page is displayed |
| `SearchCommentTest` | Searches for a comment and verifies that the comments page is displayed |

### Login

| Test Class | Type | Description |
|---|---|---|
| `LoginTest` | Positive | Logs in with valid credentials and verifies the welcome message |
| `LoginInvalidPasswordTest` | Negative | Logs in with a wrong password and verifies the error message is shown |
| `LoginNonExistentUserTest` | Negative | Logs in with a non-existent email and verifies the error message is shown |
| `LogoutTest` | Positive | Logs in then logs out, verifies redirect to the login page |

### Statistics

| Test Class | Description |
|---|---|
| `ViewStatisticsTest` | Logs in and verifies that the statistics page is displayed |

### Users

| Test Class | Description |
|---|---|
| `ViewUsersTest` | Logs in and verifies that the users page is displayed |

---

## Installation and Execution

### Prerequisites

- Java 17+
- Maven 3.6+
- Google Chrome or Firefox (for local execution)

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

Run with a specific browser (default: `chrome`):
```bash
mvn test -Dbrowser=firefox
```

Run on Selenium Grid:
```bash
mvn test -Dbrowser=remote-chrome -DgridUrl=http://localhost:4444/wd/hub
```

Run with an HTTP proxy:
```bash
mvn test -Dproxy=localhost:8080
```

> **Note:** When running tests with the `-Dproxy` option, **OWASP ZAP** must be running and listening on the specified port before the tests are started. ZAP acts as the intercepting proxy that captures and inspects HTTP traffic.
>
> To start ZAP on the default port (`8080`), launch it via the ZAP GUI or use the daemon mode:
> ```bash
> zap.sh -daemon -port 8080
> ```
> Make sure the port in the `-Dproxy` argument matches the port ZAP is configured to listen on.

Run with BrowserMob Proxy (HAR capture):
```bash
mvn test -DbrowserMobProxy=true
```

Run tests in parallel forks (multiple JVM processes):
```bash
mvn test -DforkCount=4 -DreuseForks=false
```

> **Note:** `forkCount` controls how many JVM processes Maven Surefire runs concurrently — each fork executes a subset of test classes independently, which is especially useful for the Selenium suite where each fork opens its own browser instance. `reuseForks=false` ensures each test class starts in a clean JVM instead of reusing one across classes. `forkCount` can also be set as a percentage of available CPU cores, e.g. `-DforkCount=1C`.

---

## Allure Reports

After running the tests, results are located in `target/allure-results`.

Generate the HTML report:
```bash
mvn allure:report
```

The report is generated at `target/site/allure-maven-plugin/index.html`.

Tests are organized by **Epic** and **Feature** Allure annotations:
- **Epic**: `REST API Tests` / `Selenium UI Tests`
- **Feature**: `Article Management`, `Comment Management`, `User Management`, `File Management`

On a failed Selenium test, a screenshot is automatically attached to the report.

---

## Configuration

| System Property | Default | Description |
|---|---|---|
| `baseUrl` | `http://localhost:3000/api` | Base URL for the REST API |
| `browser` | `chrome` | Browser for Selenium tests (`chrome`, `firefox`, `remote-chrome`, `remote-firefox`) |
| `gridUrl` | `http://localhost:4444/wd/hub` | Selenium Grid URL for remote execution |
| `proxy` | — | HTTP proxy server address (e.g. `localhost:8080`) |
| `browserMobProxy` | — | Enables BrowserMob Proxy for network traffic capture |
| `forkCount` | `1` | Number of parallel JVM processes Maven Surefire uses to run test classes (e.g. `4` or `1C` for one per CPU core) |
| `reuseForks` | `true` | Whether a forked JVM is reused across test classes; set to `false` for full test isolation between classes |

---

## Documentation

All key classes and methods are documented with **Javadoc** comments, including all REST and Selenium tests.

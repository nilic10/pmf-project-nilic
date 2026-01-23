# 🔧 Konfiguracija Selenium Test Framework-a

## 📋 Sadržaj

1. [Browser Konfiguracija](#browser-konfiguracija)
2. [Timeout Postavke](#timeout-postavke)
3. [URL Konfiguracija](#url-konfiguracija)
4. [Logging Postavke](#logging-postavke)
5. [Selenium Grid Konfiguracija](#selenium-grid-konfiguracija)
6. [Maven Profile Konfiguracija](#maven-profile-konfiguracija)
7. [Environment Varijable](#environment-varijable)

---

## 🌐 Browser Konfiguracija

### Dostupni Browseri

| Browser Type | Opis | Komanda |
|---|---|---|
| `chrome` | Lokalni Chrome browser | `mvn test -Dbrowser=chrome` |
| `firefox` | Lokalni Firefox browser | `mvn test -Dbrowser=firefox` |
| `remote_chrome` | Chrome na Selenium Grid-u | `mvn test -Dbrowser=remote_chrome` |
| `remote_firefox` | Firefox na Selenium Grid-u | `mvn test -Dbrowser=remote_firefox` |

### BrowserType.java

```java
public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    REMOTE_CHROME("remote_chrome"),
    REMOTE_FIREFOX("remote_firefox");
    
    // Dodaj novi browser ovde
}
```

### Kako Promeniti Browser

#### Metoda 1: Command Line
```bash
mvn clean test -Dbrowser=firefox
```

#### Metoda 2: Maven Profile
```bash
mvn clean test -Pfirefox
```

#### Metoda 3: Programski
```java
// U testu
Configuration.setBrowserType(BrowserType.FIREFOX);
```

---

## ⏱️ Timeout Postavke

### Konfiguracija (`Configuration.java`)

```java
public static final int IMPLICIT_WAIT = 10;      // Implicitni wait (sekundi)
public static final int EXPLICIT_WAIT = 15;      // Eksplicitni wait (sekundi)
public static final int PAGE_LOAD_TIMEOUT = 20;  // Page load timeout (sekundi)
```

### Šta su Timeout-ovi?

| Timeout | Opis | Korišćenje |
|---|---|---|
| **IMPLICIT_WAIT** | Globalni timeout za pronalaženje elementa | Automatski se primenjuje na sve locate operations |
| **EXPLICIT_WAIT** | Specifičan timeout za čekanje | Koristi se sa WebDriverWait u BasePage |
| **PAGE_LOAD_TIMEOUT** | Timeout za učitavanje stranice | Čeka da se stranica u potpunosti učita |

### Promenite Timeout

```java
// U Configuration.java
public static final int EXPLICIT_WAIT = 20;  // Povećano sa 15 na 20 sekundi
```

### Dinamička Promena Timeout-a

```java
// U testu
articlesPage.setImplicitWait(5);  // Provremeno smanji
// ... testiraj nešto
articlesPage.resetImplicitWait();  // Resetuj na default
```

---

## 🔗 URL Konfiguracija

### Konfiguracija (`Configuration.java`)

```java
public static final String BASE_URL = "http://localhost:3000";
public static final String ARTICLES_PAGE_URL = BASE_URL + "/articles.html";
```

### Promenite URL

```java
// Za lokalno testiranje
public static final String BASE_URL = "http://localhost:3000";

// Za staging okruženje
public static final String BASE_URL = "http://staging.example.com";

// Za produkciju
public static final String BASE_URL = "https://example.com";
```

### Dinamička Promena URL-a

```java
// U testu
articlesPage.navigateTo("http://staging.example.com/articles.html");
```

---

## 📝 Logging Postavke

### Logback Konfiguracija (`logback.xml`)

#### Log Level-i

| Level | Opis | Koristi se za |
|---|---|---|
| **DEBUG** | Detaljne informacije | Pronalaženje elemenata |
| **INFO** | Opšte informacije | Akcije korisnika, test tokovi |
| **WARN** | Upozorenja | Elementi koji nedostaju |
| **ERROR** | Greške | Exception-i i problemi |

#### Podešavanje Log Level-a

```xml
<!-- U logback.xml -->
<logger name="rs.pmf.selenium" level="DEBUG"/>  <!-- Vise informacija -->
<logger name="rs.pmf.selenium" level="INFO"/>   <!-- Normalno -->
<logger name="rs.pmf.selenium" level="WARN"/>   <!-- Samo upozorenja -->
```

#### Log Fajlovi

```
logs/
├── selenium-tests.log              # Aktivni logovi
├── selenium-tests-2024-01-23-1.log # Arhivovani
└── selenium-tests-2024-01-23-2.log # Arhivovani
```

#### Konfiguracija Archiving-a

```xml
<!-- U logback.xml -->
<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    <fileNamePattern>logs/selenium-tests-%d{yyyy-MM-dd}-%i.log</fileNamePattern>
    <maxFileSize>10MB</maxFileSize>           <!-- Max veličina po fajlu -->
    <maxHistory>30</maxHistory>               <!-- Čuvaj 30 dana -->
    <totalSizeCap>1GB</totalSizeCap>          <!-- Max ukupna veličina -->
</rollingPolicy>
```

---

## 🌐 Selenium Grid Konfiguracija

### Setup Selenium Grid Server-a

#### 1. Preuzmi Selenium Server
```bash
# Preuzmi najnovu verziju
wget https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.15.0/selenium-server-4.15.0.jar
```

#### 2. Pokreni Server
```bash
# Standalone mode
java -jar selenium-server-4.15.0.jar standalone --port 4444

# Hub mode (za distribuirane testove)
java -jar selenium-server-4.15.0.jar hub --port 4444

# Node mode (priključi se Hub-u)
java -jar selenium-server-4.15.0.jar node --hub http://localhost:4444
```

#### 3. Proverite Statusu
```bash
# Otvori u browser-u
http://localhost:4444/ui
```

### Konfiguracija Grid URL-a

```java
// U Configuration.java
public static final String gridUrl = "http://localhost:4444";

// Dinamička promena
public static void setGridUrl(String url) {
    gridUrl = url;
}
```

### Pokretanje Testova na Grid-u

```bash
# Chrome
mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://localhost:4444

# Firefox
mvn clean test -Dbrowser=remote_firefox -DgridUrl=http://localhost:4444

# Prilagođeni Grid
mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://my-grid-server.com:4444
```

---

## 📦 Maven Profile Konfiguracija

### Dostupni Profile-i

```bash
# Chrome
mvn clean test -Pchrome

# Firefox
mvn clean test -Pfirefox

# Remote Chrome
mvn clean test -Premote-chrome

# Remote Firefox
mvn clean test -Premote-firefox
```

### Profili u pom.xml

```xml
<profiles>
    <profile>
        <id>chrome</id>
        <properties>
            <browser>chrome</browser>
        </properties>
    </profile>
    
    <profile>
        <id>firefox</id>
        <properties>
            <browser>firefox</browser>
        </properties>
    </profile>
    
    <profile>
        <id>remote-chrome</id>
        <properties>
            <browser>remote_chrome</browser>
            <gridUrl>http://localhost:4444</gridUrl>
        </properties>
    </profile>
</profiles>
```

### Dodaj Novi Profile

```xml
<profile>
    <id>my-custom-profile</id>
    <properties>
        <browser>chrome</browser>
        <custom.property>value</custom.property>
    </properties>
</profile>
```

```bash
mvn clean test -Pmy-custom-profile
```

---

## 🌍 Environment Varijable

### System Properties

```bash
# Chrome
mvn clean test -Dbrowser=chrome

# Custom timeout
mvn clean test -Dbrowser=firefox -Dexplicit.wait=20

# Custom URL
mvn clean test -Dbase.url=http://staging.example.com
```

### Čitanje u Kodu

```java
// U Configuration.java
public static BrowserType getBrowserType() {
    String browser = System.getProperty("browser", "chrome");  // Default: chrome
    return BrowserType.fromString(browser);
}

// Custom property
String customValue = System.getProperty("custom.property", "default");
```

### Environment File (.env)

```properties
# .env.local
BROWSER=firefox
GRID_URL=http://localhost:4444
BASE_URL=http://localhost:3000
IMPLICIT_WAIT=10
EXPLICIT_WAIT=15
```

---

## 🔐 Best Practices

### 1. Variranje Timeout-a po Akciji

```java
// Brze akcije - manji timeout
articlesPage.setImplicitWait(5);
articlesPage.click(SEARCH_BUTTON);
articlesPage.resetImplicitWait();

// Spore akcije - veći timeout
articlesPage.setImplicitWait(30);
articlesPage.waitForPageLoad();
articlesPage.resetImplicitWait();
```

### 2. Čuvanje Konfiguracije u Extern Datotekama

```properties
# config.properties
browser=chrome
base.url=http://localhost:3000
implicit.wait=10
explicit.wait=15
```

### 3. Environment-Specifične Konfiguracije

```bash
# Development
mvn clean test -Denv=dev

# Staging
mvn clean test -Denv=staging

# Production
mvn clean test -Denv=prod
```

### 4. Logging Best Practices

```java
// Logiraj relevantne informacije
logger.info("Pronalaženje članka sa naslovom: {}", title);
logger.debug("Locator: {}", MY_LOCATOR);

// Izbegaj previše logovanja
// logger.debug("Iteracija {}", i);  // Loše - previše informacija
```

---

## 🔄 Dinamička Konfiguracija

### Konfiguracioni File

```java
// Configuration.java
public class Configuration {
    
    private static Properties properties;
    
    static {
        properties = new Properties();
        try {
            properties.load(Configuration.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            logger.error("Greška pri učitavanju config fajla", e);
        }
    }
    
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
```

---

## 🚀 Primer: Kompletan Setup

### 1. Promenite Configuration.java
```java
public static final String BASE_URL = "http://staging.example.com";
public static final int EXPLICIT_WAIT = 20;
```

### 2. Kreiraj Maven Profile
```bash
mvn clean test -Pfirefox
```

### 3. Pokrenite Testove
```bash
./run-tests.sh firefox
# ILI
mvn clean test -Dbrowser=firefox
```

### 4. Analiza Rezultata
```bash
cat logs/selenium-tests.log
```

---

## 📚 Dodatni Resursi

- [Maven Configuration](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
- [Selenium Configuration](https://www.selenium.dev/documentation/grid/configuration/)
- [Logback Configuration](http://logback.qos.ch/manual/configuration.html)
- [JUnit Configuration](https://junit.org/junit5/docs/current/user-guide/)

---

**Napomena:** Često konsultuj `Configuration.java` za centralizovane postavke! 🎯

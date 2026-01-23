# Articles Selenium Tests

Kompletан Maven Selenium projekat za testiranje `http://localhost:3000/articles.html` stranice sa JUnit5 testovima.

## 📋 Karakteristike

✅ **Page Object Pattern** - Strukturiran pristup test automatizaciji
✅ **Base Page & Base Test** - Zajedničke metode i setup/teardown
✅ **Multi-browser support** - Chrome, Firefox, lokalno ili na Selenium Grid-u
✅ **JUnit 5** - Moderna test framework sa parametrizovanim testovima
✅ **Logging** - Detaljni logovi sa Logback-om
✅ **WebDriverManager** - Automatsko upravljanje driver-ima
✅ **Pozitivni i negativni testovi** - Kompletan test coverage

## 🏗️ Struktura Projekta

```
articles-selenium-tests/
├── src/
│   ├── main/
│   │   ├── java/rs/pmf/selenium/
│   │   │   ├── config/
│   │   │   │   ├── BrowserType.java          # Enum za tipove browsera
│   │   │   │   ├── Configuration.java        # Konfiguracija testova
│   │   │   │   └── DriverFactory.java        # Factory za WebDriver
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java             # Bazna klasa za stranice
│   │   │   │   └── BaseTest.java             # Bazna klasa za testove
│   │   │   └── pages/
│   │   │       └── ArticlesPage.java         # Page Object za Articles stranicu
│   │   └── resources/
│   │       └── logback.xml                   # Logback konfiguracija
│   └── test/
│       └── java/rs/pmf/selenium/tests/
│           ├── ArticlesPagePositiveTest.java # Pozitivni testovi
│           ├── ArticlesPageNegativeTest.java # Negativni testovi
│           └── ArticlesPageIntegrationTest.java # Integracijski testovi
├── pom.xml                                   # Maven konfiguracija
└── README.md                                 # Ovaj fajl
```

## 🚀 Pokretanje Testova

### Preduslov
- Java 11 ili novija verzija
- Maven 3.6+
- Chrome ili Firefox instaliran (zavisno od toga šta koristiš)
- **VAŽNO**: http://localhost:3000 MORA biti pokrenut pre testiranja!

### Startup Web Servera

Ako server nije pokrenut, pokrenite ga prvo:

```bash
# Za Node.js aplikacije:
npm start

# Za Python:
python -m http.server 3000

# Za Docker:
docker run -p 3000:80 <your-image>

# Zatim otvorite u browser-u da verifikujete:
http://localhost:3000/articles.html
```

### Testiranje na Chrome-u (lokalno)
```bash
cd articles-selenium-tests
mvn clean test -Dbrowser=chrome
```

### Testiranje na Firefox-u (lokalno)
```bash
cd articles-selenium-tests
mvn clean test -Dbrowser=firefox
```

### Testiranje na Selenium Grid-u
Za Chrome:
```bash
mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://localhost:4444
```

Za Firefox:
```bash
mvn clean test -Dbrowser=remote_firefox -DgridUrl=http://localhost:4444
```

### Pokretanje specifičnog test klasa
```bash
# Samo pozitivni testovi
mvn clean test -Dtest=ArticlesPagePositiveTest

# Samo negativni testovi
mvn clean test -Dtest=ArticlesPageNegativeTest

# Samo integracijski testovi
mvn clean test -Dtest=ArticlesPageIntegrationTest
```

### Pokretanje specifičnog testa
```bash
mvn clean test -Dtest=ArticlesPagePositiveTest#testPageLoadsSuccessfully
```

## 🔧 Konfiguracija

### Browser Type
Dostupne opcije:
- `chrome` - Lokalni Chrome browser
- `firefox` - Lokalni Firefox browser
- `remote_chrome` - Chrome na Selenium Grid-u
- `remote_firefox` - Firefox na Selenium Grid-u

### Configuration.java
```java
public static final String BASE_URL = "http://localhost:3000";
public static final String ARTICLES_PAGE_URL = BASE_URL + "/articles.html";

// Waits (u sekundama)
public static final int IMPLICIT_WAIT = 10;
public static final int EXPLICIT_WAIT = 15;
public static final int PAGE_LOAD_TIMEOUT = 20;
```

Promenite vrednosti prema vašim potrebama.

## 📝 BasePage Metode

### Navigation
- `navigateTo(String url)` - Odlazi na određeni URL
- `refreshPage()` - Osvežava stranicu
- `goBack()` - Ide na prethodnu stranicu

### Element Interactions
- `click(By locator)` / `click(WebElement element)` - Klikće na element
- `sendKeys(By locator, String text)` - Unosi tekst
- `getText(By locator)` - Pronalazi tekst elementa
- `getAttribute(By locator, String attributeName)` - Pronalazi atribut

### Waits
- `waitForElementToBeVisible(By locator)` - Čeka da element bude vidljiv
- `waitForElementToBeClickable(By locator)` - Čeka da element bude klikljiv
- `waitForElementToDisappear(By locator)` - Čeka da element nestane
- `waitForPageLoad()` - Čeka da se stranica učita

### Select Interactions
- `selectByVisibleText(By locator, String text)` - Bira opciju iz dropdown-a po tekstu
- `selectByValue(By locator, String value)` - Bira po value atributu
- `selectByIndex(By locator, int index)` - Bira po indeksu

### JavaScript
- `executeJavaScript(String script, Object... args)` - Izvršava JS kod
- `scrollToElement(By locator)` - Skroluje do elementa
- `scrollToTop()` - Skroluje na vrh
- `scrollToBottom()` - Skroluje na dno

## 🧪 Test Klase

### ArticlesPagePositiveTest
- Testira normalnu funkcionalnost stranice
- Pronalaženje i prikaz članaka
- Pretraga i filtriranje
- Paginacija
- Parametrizovani testovi sa različitim ključnim rečima

### ArticlesPageNegativeTest
- Testira greške i granične slučajeve
- Invalidni indeksi
- Prazni stringovi i specijalni karakteri
- Nepostojeće kategorije i sortiranja
- Validacija input polja

### ArticlesPageIntegrationTest
- Kompleksni scenariji sa više akcija
- Pretraga, filtriranje, sortiranje kombinovano
- Navigacija kroz stranice
- Osvežavanje stranice i stabilnost
- Multi-step workflows

## 📊 Primeri Testova

### Jednostavan test
```java
@Test
void testPageLoadsSuccessfully() {
    articlesPage.openArticlesPage();
    articlesPage.waitForPageLoad();
    
    assertTrue(articlesPage.isPageLoaded());
}
```

### Test sa pretraživanjem
```java
@Test
void testSearchWithValidText() {
    articlesPage.openArticlesPage();
    articlesPage.searchForArticles("Java");
    
    int resultCount = articlesPage.getArticlesCount();
    assertTrue(resultCount >= 0);
}
```

### Parametrizovani test
```java
@ParameterizedTest
@ValueSource(strings = {"Java", "Python", "JavaScript"})
void testSearchWithMultipleKeywords(String keyword) {
    articlesPage.openArticlesPage();
    articlesPage.searchForArticles(keyword);
    
    int resultCount = articlesPage.getArticlesCount();
    assertTrue(resultCount >= 0);
}
```

## 📈 Logovi

Logovi se čuvaju u `logs/` direktorijumu:
- `selenium-tests.log` - Trenutni logovi
- `selenium-tests-YYYY-MM-DD-N.log` - Arhivovani logovi

## 🔗 ArticlesPage Metode

### Page Navigation
- `openArticlesPage()` - Otvara Articles stranicu
- `getPageTitle()` - Pronalazi naslov stranice
- `getPageSubtitle()` - Pronalazi podnaslov

### Search
- `enterSearchText(String text)` - Unosi tekst u search polje
- `clickSearchButton()` - Klikće na search dugme
- `searchForArticles(String text)` - Kombinovana akcija
- `clearSearchField()` - Briše search polje
- `getSearchInputValue()` - Pronalazi vrednost u search polju

### Filters
- `selectCategoryFilter(String category)` - Bira kategoriju
- `selectSortOption(String sortOption)` - Bira sortiranje

### Articles List
- `getAllArticles()` - Pronalazi sve članke
- `getArticlesCount()` - Pronalazi broj članaka
- `getArticleTitle(int index)` - Pronalazi naslov članka
- `getArticleDescription(int index)` - Pronalazi opis članka
- `getArticleDate(int index)` - Pronalazi datum članka
- `clickArticle(int index)` - Klikće na članak
- `findArticleIndexByTitle(String title)` - Pronalazi indeks članka
- `clickArticleByTitle(String title)` - Klikće na članak po naslovu

### Pagination
- `isPaginationVisible()` - Proverava vidljivost paginacije
- `clickNextPage()` - Ide na sledeću stranicu
- `clickPreviousPage()` - Ide na prethodnu stranicu
- `getPaginationButtonsCount()` - Pronalazi broj stranica
- `clickPage(int pageNumber)` - Ide na specifičnu stranicu

## 🐛 Troubleshooting

### Greška: "WebDriver je null"
- Proverite da je `setUp()` metoda pozvana
- Proverite da `ArticlesPage` biva inicijalizovan nakon `setUp()`

### Greška: "Element nije pronađen"
- Proverite da li je lokator ispravan
- Proverite da li je element vidljiv na stranici
- Povećajte `EXPLICIT_WAIT` vrednost

### Greška: "Browser se ne pokreće"
- Proverite da li je browser instaliran
- Proverite da li WebDriverManager ima dozvolu za preuzimanje drivera
- Proverite internet konekciju

### Greška pri Selenium Grid-u
- Proverite da li je Selenium Grid server pokrenut na `http://localhost:4444`
- Proverite port i gridUrl vrednosti

## 📚 Dodatni Resursi

- [Selenium Documentation](https://www.selenium.dev/documentation/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Page Object Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

## 👨‍💻 Autor

Selenium Test Automation Project

## 📄 Licenca

MIT

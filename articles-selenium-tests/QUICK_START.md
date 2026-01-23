# 🚀 Brzi Start Guide - Selenium Articles Test Framework

## 📋 Preduslov

✅ Java 11+ instaliran
✅ Maven 3.6+ instaliran
✅ Chrome ili Firefox instaliran
✅ http://localhost:3000/articles.html dostupan

---

## ⚡ Brzi Start

### 1. Otidi u projekat direktorijum

```bash
cd /Users/nilic/IdeaProjects/pmf-project-nilic/articles-selenium-tests
```

### 2. Pokreni testove na Chrome-u

```bash
# Koristi shell skript (macOS/Linux)
./run-tests.sh chrome

# ILI koristi direktno Maven
mvn clean test -Dbrowser=chrome
```

### 3. Pokreni testove na Firefox-u

```bash
./run-tests.sh firefox
# ILI
mvn clean test -Dbrowser=firefox
```

---

## 🎯 Najčešće Komande

### Pokretanje Svih Testova
```bash
./run-tests.sh all
# ILI
mvn clean test
```

### Pokretanje Samo Pozitivnih Testova
```bash
./run-tests.sh positive
# ILI
mvn clean test -Dtest=ArticlesPagePositiveTest
```

### Pokretanje Samo Negativnih Testova
```bash
./run-tests.sh negative
# ILI
mvn clean test -Dtest=ArticlesPageNegativeTest
```

### Pokretanje Samo Integracijskih Testova
```bash
./run-tests.sh integration
# ILI
mvn clean test -Dtest=ArticlesPageIntegrationTest
```

### Pokretanje Specifičnog Testa
```bash
mvn clean test -Dtest=ArticlesPagePositiveTest#testPageLoadsSuccessfully
```

---

## 🌐 Selenium Grid (Remote Testing)

### Setup Selenium Grid (prvo terminal)

```bash
# Preuzmi Selenium Server
wget https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.15.0/selenium-server-4.15.0.jar

# Pokreni server
java -jar selenium-server-4.15.0.jar standalone --port 4444
```

### Pokreni testove na Remote Grid-u

```bash
# Chrome
./run-tests.sh remote-chrome
# ILI
mvn clean test -Dbrowser=remote_chrome -DgridUrl=http://localhost:4444

# Firefox
./run-tests.sh remote-firefox
# ILI
mvn clean test -Dbrowser=remote_firefox -DgridUrl=http://localhost:4444
```

---

## 📊 Struktura Testova

### ✅ Pozitivni Testovi (`ArticlesPagePositiveTest.java`)
- Pronalaženje i učitavanje stranice
- Pronalaženje članaka
- Search funkcionalnost
- Filtriranje
- Paginacija
- Parametrizovani testovi

**Pokreni:**
```bash
mvn clean test -Dtest=ArticlesPagePositiveTest
```

### ❌ Negativni Testovi (`ArticlesPageNegativeTest.java`)
- Invalidni indeksi
- Prazni stringovi
- Specijalni karakteri
- Nepostojeće vrednosti
- Input validacija

**Pokreni:**
```bash
mvn clean test -Dtest=ArticlesPageNegativeTest
```

### 🔗 Integracijski Testovi (`ArticlesPageIntegrationTest.java`)
- Kompleksni workflow-i
- Kombinovane akcije
- Multi-step procesi
- Stabilnost nakon osvežavanja

**Pokreni:**
```bash
mvn clean test -Dtest=ArticlesPageIntegrationTest
```

---

## 📝 Primeri Korišćenja

### Primer 1: Pronalaženje Članka

```java
@Test
void testFindArticleByTitle() {
    articlesPage.openArticlesPage();
    
    // Dobij naslov prvog članka
    String targetTitle = articlesPage.getArticleTitle(0);
    
    // Pronađi ga po naslovu
    int index = articlesPage.findArticleIndexByTitle(targetTitle);
    
    // Verifikuj
    assertEquals(0, index);
}
```

### Primer 2: Pretraga sa Parametrima

```java
@ParameterizedTest
@ValueSource(strings = {"Java", "Python", "JavaScript"})
void testSearchWithKeywords(String keyword) {
    articlesPage.openArticlesPage();
    articlesPage.searchForArticles(keyword);
    
    int resultCount = articlesPage.getArticlesCount();
    assertTrue(resultCount >= 0);
}
```

### Primer 3: Kompleksni Workflow

```java
@Test
void testCompleteWorkflow() {
    // Otvori stranicu
    articlesPage.openArticlesPage();
    assertTrue(articlesPage.isPageLoaded());
    
    // Pronađi članke
    int count = articlesPage.getArticlesCount();
    assertTrue(count > 0);
    
    // Pretraga
    articlesPage.searchForArticles("test");
    Thread.sleep(1500);
    
    // Filtriranje
    articlesPage.selectCategoryFilter("Tehnologija");
}
```

---

## 🔍 Logovi i Rezultati

Logovi se čuvaju u `logs/` direktorijumu:

```
logs/
├── selenium-tests.log              # Trenutni logovi
├── selenium-tests-2024-01-23-1.log # Arhivovani logovi
├── selenium-tests-2024-01-23-2.log
└── ...
```

**Otvori logove:**
```bash
# macOS/Linux
tail -f logs/selenium-tests.log

# Ili otvori u editoru
cat logs/selenium-tests.log
```

---

## 🛠️ Konfiguracija

### Promenite Browser Type

**U konfiguraciji (`Configuration.java`):**
```java
// Dostupne opcije:
// - CHROME (default)
// - FIREFOX
// - REMOTE_CHROME
// - REMOTE_FIREFOX
```

**Iz command line-a:**
```bash
mvn clean test -Dbrowser=firefox
```

### Prilagođeni Timeout-ovi

```java
// U Configuration.java
public static final int IMPLICIT_WAIT = 10;      // sekundi
public static final int EXPLICIT_WAIT = 15;      // sekundi
public static final int PAGE_LOAD_TIMEOUT = 20;  // sekundi
```

### Promenite URL

```java
// U Configuration.java
public static final String BASE_URL = "http://localhost:3000";
public static final String ARTICLES_PAGE_URL = BASE_URL + "/articles.html";
```

---

## 🐛 Troubleshooting

### Problem: "Element nije pronađen"

```bash
# Povećaj explicit wait timeout
# U Configuration.java
public static final int EXPLICIT_WAIT = 20;  // Bilo je 15
```

### Problem: "Browser se ne pokreće"

```bash
# Proverite da li je browser instaliran
which google-chrome      # Chrome (Linux)
which firefox            # Firefox

# Ili koristite Firefox umesto Chrome-a
mvn clean test -Dbrowser=firefox
```

### Problem: "Selenium Grid nije dostupan"

```bash
# Pokrenite Selenium Server prvi
java -jar selenium-server-4.15.0.jar standalone --port 4444

# Zatim pokreni testove
mvn clean test -Dbrowser=remote_chrome
```

### Problem: "WebDriver je null"

```bash
# Proverite da BaseTest.setUp() postavlja driver
# Proverite da ArticlesPage biva inicijalizovan nakon setUp()
```

---

## 📚 Dostupne Page Object Metode

### Navigation
- `openArticlesPage()` - Otvori stranicu
- `navigateTo(String url)` - Idi na URL
- `refreshPage()` - Osvežaj stranicu
- `goBack()` - Nazad

### Search
- `searchForArticles(String text)` - Pretraži
- `enterSearchText(String text)` - Unos u search polje
- `clickSearchButton()` - Klikni search
- `clearSearchField()` - Očisti polje

### Articles
- `getAllArticles()` - Dobij sve članke
- `getArticlesCount()` - Broj članaka
- `getArticleTitle(int index)` - Naslov članka
- `getArticleDescription(int index)` - Opis članka
- `getArticleDate(int index)` - Datum članka
- `clickArticle(int index)` - Klikni na članak
- `findArticleIndexByTitle(String title)` - Pronađi po naslovu

### Filters
- `selectCategoryFilter(String category)` - Filtriraj po kategoriji
- `selectSortOption(String sortOption)` - Sortiraj

### Pagination
- `clickNextPage()` - Sledeća stranica
- `clickPreviousPage()` - Prethodna stranica
- `getPaginationButtonsCount()` - Broj stranica

---

## 💡 Korisni Saveti

### 1. Razvoj Novog Testa
```bash
# Kreiraj novi test u test/ direktorijumu
# Nasledi BaseTest klasu
# Koristi ArticlesPage za interaction
```

### 2. Dodaj Novu Metodu u ArticlesPage
```java
public void myNewMethod() {
    logger.info("Izvršavanje moje nove metode");
    // Koristi metode iz BasePage
    click(MY_LOCATOR);
    waitForElementToBeVisible(OTHER_LOCATOR);
}
```

### 3. Debug Mode
```bash
# Pokreni samo jedan test i vidi logove
mvn clean test -Dtest=ArticlesPagePositiveTest#testPageLoadsSuccessfully
```

### 4. Spremi Screenshot-a (opciono)
```java
// Dodaj u BasePage
public void takeScreenshot(String filename) {
    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(screenshot, new File("screenshots/" + filename + ".png"));
}
```

---

## 🚀 CI/CD Integracija

### GitHub Actions Primer

```yaml
name: Selenium Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: cd articles-selenium-tests
      - run: mvn clean test -Dbrowser=chrome
```

---

## 📞 Pomoć i Resursi

- [Selenium dokumentacija](https://www.selenium.dev/documentation/)
- [JUnit 5 dokumentacija](https://junit.org/junit5/docs/current/user-guide/)
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager)
- [Page Object Pattern](https://www.selenium.dev/documentation/test_practices/encouraged/page_object_models/)

---

## ✨ Sledećih Koraka

1. ✅ Instalacija i setup
2. ✅ Pokreni `./run-tests.sh chrome`
3. ✅ Pogledaj logove u `logs/selenium-tests.log`
4. ✅ Kreiraj nove testove
5. ✅ Dodaj u CI/CD pipeline

---

**Sretno sa testiranjem! 🎉**

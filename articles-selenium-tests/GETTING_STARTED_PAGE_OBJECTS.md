# 🎯 Getting Started - Page Objects za Articles Selenium Testove

## 📍 Lokacija Projekta

```
/Users/nilic/IdeaProjects/pmf-project-nilic/articles-selenium-tests/
```

## 📦 Šta je Kreirano

### ✅ 6 Page Object Klasa (1659 linija koda)

```
src/main/java/rs/pmf/selenium/pages/
├── LandingPage.java              (230 linija, ~20 metoda)
├── ArticlesPage.java             (289 linija, ~25 metoda)
├── CommentsPage.java             (276 linija, ~25 metoda)
├── SingleArticlePage.java        (250 linija, ~20 metoda)
├── SingleCommentPage.java        (227 linija, ~20 metoda)
└── PageObjectExamples.java       (387 linija, 10 primena)
```

### ✅ 4 Dokumentacijska Fajla

1. **PAGE_OBJECTS_README.md** - Kompletna dokumentacija
2. **PAGE_OBJECTS_DOCUMENTATION.md** - Detaljni reference
3. **PAGE_OBJECTS_SUMMARY.txt** - Statistika i pregled
4. **QUICK_REFERENCE.md** - Brzi vodič sa primerima

---

## 🚀 Brz Start (5 minuta)

### Korak 1: Inicijalizacija

```java
import rs.pmf.selenium.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

// Kreiraj driver
WebDriver driver = new ChromeDriver();

// Kreiraj page objekte
LandingPage landingPage = new LandingPage(driver);
ArticlesPage articlesPage = new ArticlesPage(driver);
```

### Korak 2: Početak Aplikacije

```java
// Kreni sa Landing Page
landingPage.navigateToLandingPage("http://localhost:3000/");

// Proveri da li je učitana
assertTrue(landingPage.isPageFullyLoaded());

// Klikni na "Let's Start"
landingPage.clickLetsStartButton();
```

### Korak 3: Pregledaj Članke

```java
// Čekaj da se učitaju
articlesPage.waitForArticlesToLoad();

// Preuzmi podatke
int count = articlesPage.getArticlesCount();
String title = articlesPage.getArticleTitle(0);

System.out.println("Broja članaka: " + count);
System.out.println("Naslov prvog članka: " + title);
```

### Korak 4: Otvori Članak

```java
// Klikni na prvi članak
articlesPage.clickArticleTitle(0);

// Čekaj da se učita
SingleArticlePage singleArticlePage = new SingleArticlePage(driver);
singleArticlePage.waitForArticleToLoad();

// Preuzmi detalje
String content = singleArticlePage.getArticleContent();
String author = singleArticlePage.getArticleAuthor();

System.out.println("Autor: " + author);
System.out.println("Sadržaj: " + content.substring(0, 100) + "...");
```

### Korak 5: Zatvori Browser

```java
driver.quit();
```

---

## 📚 Detaljniji Primeri

### Primer 1: Pretraga Članaka

```java
public void searchAndDisplayArticles() {
    ArticlesPage page = new ArticlesPage(driver);
    
    // Pretraga
    page.searchArticles("testing");
    page.waitForArticlesToLoad();
    
    // Prikazi rezultate
    System.out.println("Pronađeno: " + page.getArticlesCount());
    for (int i = 0; i < page.getArticlesCount(); i++) {
        System.out.println((i + 1) + ". " + page.getArticleTitle(i));
    }
}
```

### Primer 2: Paginacija i Sortiranje

```java
public void browseAllArticles() {
    ArticlesPage page = new ArticlesPage(driver);
    
    // Postavi broj po stranici
    page.setItemsPerPage("20");
    
    // Sortiraj po datumu
    page.sortBy("date");
    
    // Premotaj kroz sve stranice
    int pageNum = 1;
    while (page.isNextButtonAvailable()) {
        System.out.println("Stranica " + pageNum);
        System.out.println("Broj članaka: " + page.getArticlesCount());
        page.clickNext();
        pageNum++;
    }
}
```

### Primer 3: Preuzimanje Članka

```java
public void downloadArticle() {
    ArticlesPage articlesPage = new ArticlesPage(driver);
    SingleArticlePage singlePage = new SingleArticlePage(driver);
    
    // Otvori članak
    articlesPage.clickArticleTitle(0);
    singlePage.waitForArticleToLoad();
    
    // Preuzmi kao CSV
    if (singlePage.isCSVDownloadAvailable()) {
        singlePage.downloadAsCSV();
        System.out.println("CSV preuzet");
    }
    
    // Preuzmi kao JSON
    if (singlePage.isJSONDownloadAvailable()) {
        singlePage.downloadAsJSON();
        System.out.println("JSON preuzet");
    }
}
```

### Primer 4: Pregledanje Komentara

```java
public void browseComments() {
    ArticlesPage articlesPage = new ArticlesPage(driver);
    CommentsPage commentsPage = new CommentsPage(driver);
    
    // Idi na Comments tab
    articlesPage.goToComments();
    commentsPage.waitForCommentsToLoad();
    
    // Preuzmi komentare
    System.out.println("Broja komentara: " + commentsPage.getCommentsCount());
    
    for (int i = 0; i < Math.min(5, commentsPage.getCommentsCount()); i++) {
        String author = commentsPage.getCommentAuthor(i);
        String text = commentsPage.getCommentText(i);
        System.out.println(author + ": " + text.substring(0, 50) + "...");
    }
}
```

### Primer 5: Kompletan Workflow

```java
public void completeWorkflow() {
    // Inicijalizacija
    LandingPage landingPage = new LandingPage(driver);
    ArticlesPage articlesPage = new ArticlesPage(driver);
    SingleArticlePage singlePage = new SingleArticlePage(driver);
    CommentsPage commentsPage = new CommentsPage(driver);
    SingleCommentPage singleCommentPage = new SingleCommentPage(driver);
    
    // 1. Landing Page
    landingPage.navigateToLandingPage("http://localhost:3000/");
    assertTrue(landingPage.isPageFullyLoaded());
    landingPage.clickLetsStartButton();
    
    // 2. Articles
    articlesPage.waitForArticlesToLoad();
    int articleCount = articlesPage.getArticlesCount();
    System.out.println("Broja članaka: " + articleCount);
    
    // 3. Single Article
    articlesPage.clickArticleTitle(0);
    singlePage.waitForArticleToLoad();
    String articleTitle = singlePage.getArticleTitle();
    System.out.println("Članak: " + articleTitle);
    
    // 4. Comments
    singlePage.returnToArticles();
    articlesPage.goToComments();
    commentsPage.waitForCommentsToLoad();
    int commentCount = commentsPage.getCommentsCount();
    System.out.println("Broja komentara: " + commentCount);
    
    // 5. Single Comment
    if (commentCount > 0) {
        commentsPage.clickSeeCommentForComment(0);
        singleCommentPage.waitForCommentToLoad();
        String commentText = singleCommentPage.getCommentText();
        System.out.println("Komentar: " + commentText.substring(0, 50) + "...");
    }
}
```

---

## 🧪 Test Framework Integracija

### Korišćenje sa TestNG

```java
import org.testng.annotations.*;
import org.testng.Assert;
import rs.pmf.selenium.pages.*;

public class ArticleTests {
    
    private WebDriver driver;
    private ArticlesPage articlesPage;
    
    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        articlesPage = new ArticlesPage(driver);
    }
    
    @Test
    public void testArticleSearch() {
        articlesPage.searchArticles("testing");
        Assert.assertTrue(articlesPage.getArticlesCount() > 0);
    }
    
    @Test
    public void testArticleTitle() {
        String title = articlesPage.getArticleTitle(0);
        Assert.assertNotNull(title);
        Assert.assertTrue(title.length() > 0);
    }
    
    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
```

### Korišćenje sa JUnit

```java
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import rs.pmf.selenium.pages.*;

public class CommentsTest {
    
    private WebDriver driver;
    private CommentsPage commentsPage;
    
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        commentsPage = new CommentsPage(driver);
    }
    
    @Test
    public void testCommentCount() {
        int count = commentsPage.getCommentsCount();
        assertTrue(count > 0);
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
}
```

---

## 📖 Dokumentacija za Čitanje

Preporučeni redosled čitanja:

1. **QUICK_REFERENCE.md** (8 min) - Primeri i brzi vodič
2. **PAGE_OBJECTS_README.md** (15 min) - Pregled svih klasa
3. **PAGE_OBJECTS_DOCUMENTATION.md** (20 min) - Detaljni reference

---

## 🎯 Česte Akcije

| Akcija | Kod |
|--------|-----|
| Navigacija na stranicu | `page.navigateTo(url)` |
| Pronalaženje elementa | `page.findElement(locator)` |
| Klik na element | `page.click(locator)` |
| Unos teksta | `page.sendKeys(locator, text)` |
| Preuzimanje teksta | `page.getText(locator)` |
| Čekanje na element | `page.waitForElementToBeVisible(locator)` |
| Skrolovanje | `page.scrollToElement(locator)` |
| Provera vidljivosti | `page.isElementVisible(locator)` |

---

## 🔧 Konfiguracija

### Preuzmi WebDriver

```bash
# ChromeDriver
# https://chromedriver.chromium.org/

# FirefoxDriver
# https://github.com/mozilla/geckodriver/releases

# Postavi PATH ili koristi WebDriverManager
<dependency>
    <groupId>io.github.bonigarcia</groupId>
    <artifactId>webdrivermanager</artifactId>
    <version>5.6.2</version>
</dependency>
```

### Automatsko Pronalaženje WebDriver-a

```java
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
    public static WebDriver setUp() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
```

---

## ⚠️ Česta Greška i Rešenja

### Greška 1: ElementNotFound

**Uzrok**: Element je još uvek učitavanja  
**Rešenje**:
```java
page.waitForElementToBeVisible(locator);
page.waitForPageLoad();
```

### Greška 2: StaleElementReference

**Uzrok**: Elementa nije dostupna nakon što je stranica osvežena  
**Rešenje**:
```java
// Preuzmi ponovo
WebElement element = page.findElement(locator);
page.click(element);
```

### Greška 3: Index OutOfBounds

**Uzrok**: Pristup elementu koji ne postoji  
**Rešenje**:
```java
int count = page.getArticlesCount();
if (count > index) {
    String title = page.getArticleTitle(index);
}
```

### Greška 4: Timeout

**Uzrok**: Element se ne učitava dovoljno brzo  
**Rešenje**:
```java
page.setImplicitWait(15); // Povećaj timeout
page.waitForElementToBeVisible(locator);
```

---

## 💡 Best Practices

✅ **Uvek čekaj pre nego što interaguj sa elementom**
```java
page.waitForElementToBeVisible(locator);
page.click(locator);
```

✅ **Proveri da li element postoji pre pristupa**
```java
if (page.isElementPresent(locator)) {
    page.click(locator);
}
```

✅ **Koristi descriptivne nazive**
```java
String articleTitle = page.getArticleTitle(0);  // ✅ Dobro
String title = page.getText(locator);            // ❌ Loše
```

✅ **Uvek zatvori browser**
```java
try {
    // Test kod
} finally {
    driver.quit();
}
```

✅ **Logiraj važne akcije**
```java
logger.info("Pretraga članka: {}", keyword);
articlesPage.searchArticles(keyword);
```

---

## 📊 Page Object Struktura

```
BasePage (osnovna klasa)
├── LandingPage (početna stranica)
├── ArticlesPage (lista članaka)
├── CommentsPage (lista komentara)
├── SingleArticlePage (detalji članka)
└── SingleCommentPage (detalji komentara)
```

---

## 🚦 Sledeći Koraci

1. ✅ Pregleda dokumentaciju
2. ✅ Radi kroz primere u QUICK_REFERENCE.md
3. ✅ Kreiraj svoje test klase
4. ✅ Pokreni testove
5. ✅ Dodaj nove page objekte kako proširuješ testove

---

## 📞 Pomoć i Pitanja

Za više informacija, pogledaj:
- `PAGE_OBJECTS_README.md` - Kompletna dokumentacija
- `PAGE_OBJECTS_DOCUMENTATION.md` - Detaljni reference
- `PageObjectExamples.java` - 10 kompletan primena
- `QUICK_REFERENCE.md` - Brzi vodič

---

**Status**: ✅ Sve je spremno za korišćenje  
**Verzija**: 1.0  
**Kreirano**: Januar 2026


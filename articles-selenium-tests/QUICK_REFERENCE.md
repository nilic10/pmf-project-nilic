# Page Objects - Quick Reference Guide

## 🚀 Brz Vodič za Korišćenje

### 1. Inicijalizacija

```java
// Importi
import rs.pmf.selenium.pages.*;
import org.openqa.selenium.WebDriver;

// Kreiranje page objekata
WebDriver driver = new ChromeDriver();
LandingPage landingPage = new LandingPage(driver);
ArticlesPage articlesPage = new ArticlesPage(driver);
CommentsPage commentsPage = new CommentsPage(driver);
SingleArticlePage singleArticlePage = new SingleArticlePage(driver);
SingleCommentPage singleCommentPage = new SingleCommentPage(driver);
```

### 2. Landing Page - Početak

```java
// Navigacija
landingPage.navigateToLandingPage("http://localhost:3000/");

// Provera
assertTrue(landingPage.isPageFullyLoaded());

// Info
String title = landingPage.getPageTitle();
String version = landingPage.getFooterVersion();

// Početak aplikacije
landingPage.clickLetsStartButton();
```

### 3. Articles - Lista Članaka

```java
// Osnovne akcije
int count = articlesPage.getArticlesCount();
String title = articlesPage.getArticleTitle(0);
String author = articlesPage.getArticleAuthor(0);
articlesPage.clickArticleTitle(0);

// Pretraga
articlesPage.searchArticles("Testing");
articlesPage.clearSearch();

// Sortiranje
articlesPage.sortBy("date");
articlesPage.sortBy("author");

// Paginacija
if (articlesPage.isNextButtonAvailable()) {
    articlesPage.clickNext();
}
if (articlesPage.isPrevButtonAvailable()) {
    articlesPage.clickPrev();
}

// Filtriranje
articlesPage.setItemsPerPage("12");
```

### 4. Comments - Lista Komentara

```java
// Osnovne akcije
int count = commentsPage.getCommentsCount();
String text = commentsPage.getCommentText(0);
String author = commentsPage.getCommentAuthor(0);
String article = commentsPage.getCommentArticle(0);

// Pretraga
commentsPage.searchComments("development");
commentsPage.clearSearch();

// Sortiranje
commentsPage.sortBy("date");

// Paginacija
commentsPage.clickNext();
commentsPage.clickPrev();

// Otvaranje komentara
commentsPage.clickSeeCommentForComment(0);
```

### 5. Single Article - Detalji Članka

```java
// Metadata
String title = singleArticlePage.getArticleTitle();
String author = singleArticlePage.getArticleAuthor();
String date = singleArticlePage.getArticleDate();
String id = singleArticlePage.getArticleId();

// Sadržaj
String content = singleArticlePage.getArticleContent();
int paragraphs = singleArticlePage.getContentParagraphsCount();
boolean hasImage = singleArticlePage.isArticleImageVisible();

// Download
if (singleArticlePage.isCSVDownloadAvailable()) {
    singleArticlePage.downloadAsCSV();
}
if (singleArticlePage.isJSONDownloadAvailable()) {
    singleArticlePage.downloadAsJSON();
}
if (singleArticlePage.isPDFDownloadAvailable()) {
    singleArticlePage.downloadAsPDF();
}

// Povratak
singleArticlePage.returnToArticles();
```

### 6. Single Comment - Detalji Komentara

```java
// Metadata
String id = singleCommentPage.getCommentId();
String author = singleCommentPage.getCommentAuthor();
String date = singleCommentPage.getCommentDate();

// Tekst
String text = singleCommentPage.getCommentText();
int length = singleCommentPage.getCommentTextLength();
boolean contains = singleCommentPage.isCommentTextContains("testing");

// Članak
String articleTitle = singleCommentPage.getArticleTitle();
singleCommentPage.clickArticleLink();

// Povratak
singleCommentPage.returnToComments();
```

---

## 📋 Česta Korišćenja

### Scenario 1: Pregled članaka

```java
articlesPage.navigateToArticles();
articlesPage.waitForArticlesToLoad();

for (int i = 0; i < articlesPage.getArticlesCount(); i++) {
    String title = articlesPage.getArticleTitle(i);
    String author = articlesPage.getArticleAuthor(i);
    System.out.println(title + " by " + author);
}
```

### Scenario 2: Pretraga i Paginacija

```java
articlesPage.searchArticles("development");
int resultCount = articlesPage.getArticlesCount();

while (articlesPage.isNextButtonAvailable()) {
    System.out.println("Stranica sa " + resultCount + " rezultata");
    articlesPage.clickNext();
    resultCount = articlesPage.getArticlesCount();
}
```

### Scenario 3: Otvaranje i Preuzimanje

```java
articlesPage.clickArticleTitle(0);
singleArticlePage.waitForArticleToLoad();

if (singleArticlePage.isDownloadSectionVisible()) {
    singleArticlePage.downloadAsCSV();
    Thread.sleep(1000); // Čekaj download
    singleArticlePage.downloadAsJSON();
    Thread.sleep(1000);
    singleArticlePage.downloadAsPDF();
}

singleArticlePage.returnToArticles();
```

### Scenario 4: Kompletan Workflow

```java
// Landing
landingPage.navigateToLandingPage("http://localhost:3000/");
landingPage.clickLetsStartButton();

// Articles
articlesPage.searchArticles("testing");
articlesPage.clickArticleTitle(0);

// Article Details
String content = singleArticlePage.getArticleContent();
singleArticlePage.returnToArticles();

// Comments
articlesPage.goToComments();
commentsPage.searchComments("test");
commentsPage.clickSeeCommentForComment(0);

// Comment Details
String commentText = singleCommentPage.getCommentText();
singleCommentPage.clickArticleLink();

// Back to Article
String articleTitle = singleArticlePage.getArticleTitle();
```

---

## 🧪 Test Primeri

### Test 1: Pretraga Članaka

```java
@Test
public void testArticleSearch() {
    landingPage.navigateToLandingPage("http://localhost:3000/");
    landingPage.clickLetsStartButton();
    
    articlesPage.searchArticles("Testing");
    int resultCount = articlesPage.getArticlesCount();
    
    assertTrue(resultCount > 0, "Trebalo bi pronađeno najmanje jedan članak");
    String title = articlesPage.getArticleTitle(0);
    assertTrue(title.contains("Testing") || title.contains("testing"));
}
```

### Test 2: Download članka

```java
@Test
public void testArticleDownload() {
    articlesPage.clickArticleTitle(0);
    singleArticlePage.waitForArticleToLoad();
    
    assertTrue(singleArticlePage.isCSVDownloadAvailable());
    assertTrue(singleArticlePage.isJSONDownloadAvailable());
    assertTrue(singleArticlePage.isPDFDownloadAvailable());
    
    singleArticlePage.downloadAsCSV();
}
```

### Test 3: Navigacija članaka

```java
@Test
public void testArticleNavigation() {
    articlesPage.waitForArticlesToLoad();
    int initialCount = articlesPage.getArticlesCount();
    
    articlesPage.clickArticleTitle(0);
    assertTrue(singleArticlePage.isOnSingleArticlePage());
    
    singleArticlePage.returnToArticles();
    assertEquals(initialCount, articlesPage.getArticlesCount());
}
```

### Test 4: Komentari

```java
@Test
public void testCommentNavigation() {
    articlesPage.goToComments();
    commentsPage.waitForCommentsToLoad();
    
    assertTrue(commentsPage.isCommentsTabActive());
    assertTrue(commentsPage.getCommentsCount() > 0);
    
    commentsPage.clickSeeCommentForComment(0);
    assertTrue(singleCommentPage.isOnSingleCommentPage());
}
```

---

## 🔍 Česti Problemi

### Problem: Element nije pronađen
```java
// Čekaj da element bude vidljiv
articlesPage.waitForArticlesToLoad();
articlesPage.waitForElementToBeVisible(ARTICLE_CARDS);
```

### Problem: Index van granica
```java
// Provera pre pristupa
int count = articlesPage.getArticlesCount();
if (count > 0) {
    String title = articlesPage.getArticleTitle(0);
}
```

### Problem: Timeout
```java
// Povećaj čekanje
singleArticlePage.waitForArticleToLoad();
singleArticlePage.setImplicitWait(15); // Povećaj na 15 sekundi
```

---

## 📊 Dostupne Metode po Tipu

### Navigacijske Metode
- `navigateToArticles()`, `navigateToComments()`
- `goToArticles()`, `goToComments()`
- `returnToArticles()`, `returnToComments()`
- `clickArticleLink()`, `clickArticleTitle(int)`

### Pretraga i Sortiranje
- `searchArticles()`, `searchComments()`
- `sortBy()`, `setItemsPerPage()`
- `clearSearch()`

### Pronalaženje Podataka
- `getArticlesCount()`, `getCommentsCount()`
- `getArticleTitle()`, `getCommentText()`
- `getArticleAuthor()`, `getCommentAuthor()`

### Čekanja
- `waitForPageLoad()`, `waitForArticlesToLoad()`
- `waitForCommentsToLoad()`, `waitForArticleToLoad()`
- `waitForCommentToLoad()`

### Provere
- `isArticlesTabActive()`, `isCommentsTabActive()`
- `isOnSingleArticlePage()`, `isOnSingleCommentPage()`
- `isCSVDownloadAvailable()`, `isArticleImageVisible()`

---

## 💡 Best Practices

✅ Uvek čekaj da se element učita pre nego što ga koristiš
✅ Proveri dostupnost elementa pre pristupa
✅ Koristi descriptivne nazive varijabli
✅ Logiraj akcije za debug
✅ Koristi try-catch za kritične akcije
✅ Zatvori browser nakon testiranja

```java
WebDriver driver = null;
try {
    driver = new ChromeDriver();
    // Test kod
} finally {
    if (driver != null) {
        driver.quit();
    }
}
```

---

**Verzija**: 1.0  
**Poslednja Ažuriranja**: Januar 2026

# Page Objects za Articles Selenium Testove

Kompletan set Page Object klasa za testiranje aplikacije na `http://localhost:3000/`.

## 📋 Sadržaj

Ovaj direktorijum sadrži 6 kreiranih Page Object klasa:

```
src/main/java/rs/pmf/selenium/pages/
├── LandingPage.java              (230 linija) - Početna stranica
├── ArticlesPage.java             (289 linija) - Lista članaka
├── CommentsPage.java             (276 linija) - Lista komentara
├── SingleArticlePage.java        (250 linija) - Detalji članka
├── SingleCommentPage.java        (227 linija) - Detalji komentara
└── PageObjectExamples.java       (387 linija) - Primeri korišćenja
```

**Ukupno**: 1659 linija koda sa ~110+ metoda

---

## 🎯 Pregled Page Klasa

### 1. **LandingPage** - Početna stranica
Predstavlja početni ekran sa "Let's Start" dugmeta.

**Ključne metode:**
- `clickLetsStartButton()` - Započinji aplikaciju
- `isPageFullyLoaded()` - Provera da li je stranica učitana
- `getPageTitle()`, `getPageDescription()` - Preuzimanje sadržaja
- `getFooterVersion()` - Verzija aplikacije

**Locators:**
- "Let's Start" dugme
- Naslov stranice
- Footer informacije

---

### 2. **ArticlesPage** - Lista članaka
Omogućava pregledanje, pretragu, sortiranje i paginaciju članaka.

**Ključne metode:**
- `getArticlesCount()` - Broj vidljivih članaka
- `getArticleTitle(int index)` - Naslov članka
- `getArticleAuthor(int index)` - Autor članka
- `clickArticleTitle(int index)` - Otvori članak
- `searchArticles(String keyword)` - Pretraži članke
- `sortBy(String option)` - Sortiraj po opciji
- `setItemsPerPage(String count)` - Postavi broj po stranici
- `clickNext()`, `clickPrev()` - Navigacija kroz stranice

**Locators:**
- Članak kartice
- Naslov, autor, datum, opis članka
- Paginacijski dugmići (Next, Prev)
- Search polje
- Sortiranje dropdown
- Items per page select

---

### 3. **CommentsPage** - Lista komentara
Omogućava pregledanje, pretragu, sortiranje i paginaciju komentara.

**Ključne metode:**
- `getCommentsCount()` - Broj vidljivih komentara
- `getCommentText(int index)` - Tekst komentara
- `getCommentAuthor(int index)` - Autor komentara
- `getCommentArticle(int index)` - Članak na kojem je komentar
- `clickSeeCommentForComment(int index)` - Otvori komentar
- `searchComments(String keyword)` - Pretraži komentare
- `sortBy(String option)` - Sortiraj po opciji

**Locators:**
- Komentar kartice
- Tekst, autor, datum komentara
- Članak na kojem je komentar
- Paginacijski dugmići
- Search polje
- Sortiranje dropdown

---

### 4. **SingleArticlePage** - Detalji članka
Prikazuje kompletan članak sa svim detaljima i opcijama.

**Ključne metode:**
- `getArticleTitle()` - Naslov članka
- `getArticleAuthor()` - Autor članka
- `getArticleDate()` - Datum objave
- `getArticleContent()` - Kompletan tekst
- `getContentParagraphsCount()` - Broj paragrafa
- `isArticleImageVisible()` - Da li je slika vidljiva
- `downloadAsCSV()`, `downloadAsJSON()`, `downloadAsPDF()` - Preuzimanje
- `returnToArticles()` - Povratak na listu
- `isArticleFullyLoaded()` - Provera da li je učitan

**Locators:**
- Naslov članka
- Slika članka
- Metadata (ID, autor, datum)
- Sadržaj članka
- Download dugmići (CSV, JSON, PDF)
- Link za povratak

---

### 5. **SingleCommentPage** - Detalji komentara
Prikazuje kompletan komentar sa svim informacijama.

**Ključne metode:**
- `getCommentText()` - Tekst komentara
- `getCommentAuthor()` - Autor komentara
- `getCommentDate()` - Datum komentara
- `getCommentId()` - ID komentara
- `getArticleTitle()` - Naslov članka na kojem je komentar
- `clickArticleLink()` - Otvori članak
- `returnToComments()` - Povratak na komentare
- `isCommentFullyLoaded()` - Provera da li je učitan

**Locators:**
- Metadata komentara (ID, autor, datum)
- Tekst komentara
- Članak na kojem je komentar
- Link na članak
- Link za povratak

---

### 6. **PageObjectExamples** - Primeri korišćenja
Referentna klasa sa 10 detaljnih primera kako koristiti sve page objekte.

**Primeri:**
1. Landing Page navigacija
2. Pregledanje članaka
3. Pretraga članaka
4. Paginacija članaka
5. Otvaranje detaljne stranice članka
6. Pregledanje komentara
7. Sortiranje komentara
8. Otvaranje detaljne stranice komentara
9. Kompletan tok navigacije
10. Napredne operacije (pretraga + filtriranje)

---

## 🚀 Kako Koristiti

### Osnovni Primer

```java
import rs.pmf.selenium.pages.*;

// Inicijalizacija
WebDriver driver = new ChromeDriver();
LandingPage landingPage = new LandingPage(driver);
ArticlesPage articlesPage = new ArticlesPage(driver);

// Navigacija
landingPage.navigateToLandingPage("http://localhost:3000/");
landingPage.clickLetsStartButton();

// Rad sa člancima
int count = articlesPage.getArticlesCount();
String title = articlesPage.getArticleTitle(0);
articlesPage.clickArticleTitle(0);
```

### Test Primer

```java
@Test
public void testArticleSearch() {
    LandingPage landingPage = new LandingPage(driver);
    ArticlesPage articlesPage = new ArticlesPage(driver);
    
    // Setup
    landingPage.navigateToLandingPage("http://localhost:3000/");
    landingPage.clickLetsStartButton();
    
    // Test
    articlesPage.searchArticles("Testing");
    int resultCount = articlesPage.getArticlesCount();
    
    // Assert
    assertTrue(resultCount > 0, "Trebalo bi pronađeno najmanje jedan članak");
}
```

### Kompletan Workflow

```java
@Test
public void testCompleteWorkflow() {
    // Inicijalizacija svih page objekata
    LandingPage landingPage = new LandingPage(driver);
    ArticlesPage articlesPage = new ArticlesPage(driver);
    SingleArticlePage singleArticlePage = new SingleArticlePage(driver);
    CommentsPage commentsPage = new CommentsPage(driver);
    SingleCommentPage singleCommentPage = new SingleCommentPage(driver);
    
    // 1. Landing Page
    landingPage.navigateToLandingPage("http://localhost:3000/");
    assertTrue(landingPage.isPageFullyLoaded());
    landingPage.clickLetsStartButton();
    
    // 2. Articles
    assertTrue(articlesPage.isArticlesTabActive());
    int articleCount = articlesPage.getArticlesCount();
    assertTrue(articleCount > 0);
    
    // 3. Single Article
    articlesPage.clickArticleTitle(0);
    assertTrue(singleArticlePage.isOnSingleArticlePage());
    String title = singleArticlePage.getArticleTitle();
    assertNotNull(title);
    
    // 4. Comments
    singleArticlePage.returnToArticles();
    articlesPage.goToComments();
    assertTrue(commentsPage.isCommentsTabActive());
    
    // 5. Single Comment
    if (commentsPage.getCommentsCount() > 0) {
        commentsPage.clickSeeCommentForComment(0);
        assertTrue(singleCommentPage.isOnSingleCommentPage());
        String commentText = singleCommentPage.getCommentText();
        assertNotNull(commentText);
    }
}
```

---

## 📚 Nasleđene Metode (iz BasePage)

Sve page klase nasleđuju ove metode:

### Navigacija
- `navigateTo(String url)` - Otvori URL
- `refreshPage()` - Osvežavanje
- `goBack()` - Nazad

### Pronalaženje
- `findElement(By locator)` - Pronađi element
- `findElements(By locator)` - Pronađi sve elemente

### Interakcija
- `click(By locator)` - Klik
- `sendKeys(By locator, String text)` - Unos teksta
- `getText(By locator)` - Preuzimanje teksta
- `getAttribute(By locator, String attr)` - Preuzimanje atributa
- `clearField(By locator)` - Brisanje teksta

### Čekanja
- `waitForElementToBeVisible(By locator)` - Čekaj vidljivost
- `waitForElementToBeClickable(By locator)` - Čekaj klikljivost
- `waitForPageLoad()` - Čekaj učitavanje stranice

### Dropdown
- `selectByVisibleText(By locator, String text)` - Izbor po tekstu
- `selectByValue(By locator, String value)` - Izbor po value
- `selectByIndex(By locator, int index)` - Izbor po indexu

### JavaScript
- `scrollToElement(By locator)` - Skrolovanje do elementa
- `scrollToTop()` - Skrolovanje na vrh
- `scrollToBottom()` - Skrolovanje na dno

---

## 🔍 Struktura Locatora

Svi locatori su definisani kao `private static final By` za:
- ✅ Lakšu održavanju
- ✅ Centralizovanu promenu
- ✅ Bolju čitljivost koda
- ✅ XPath baziorane pozicije (fleksibilne)

**Primer lokator definicije:**
```java
private static final By ARTICLE_TITLE = By.xpath("//h1 | //h2[contains(@class, 'title')]");
private static final By SEARCH_INPUT = By.xpath("//input[contains(@placeholder, 'Search')]");
private static final By NEXT_BUTTON = By.xpath("//a[contains(text(), 'Next')]");
```

---

## 📊 Statistika

| Klasa | Linije | Metode | Locatori |
|-------|--------|--------|----------|
| LandingPage | 230 | ~20 | 7 |
| ArticlesPage | 289 | ~25 | 12 |
| CommentsPage | 276 | ~25 | 10 |
| SingleArticlePage | 250 | ~20 | 10 |
| SingleCommentPage | 227 | ~20 | 8 |
| PageObjectExamples | 387 | 10 | - |
| **UKUPNO** | **1659** | **~120** | **47** |

---

## 🛠️ Karakteristike

✅ **Kompletno Dokumentovane** - Svaka metoda ima JavaDoc  
✅ **Logovane** - SLF4J logger za svaku akciju  
✅ **Robusan Kod** - Explicit waits i error handling  
✅ **Page Object Pattern** - Jasna separacija koda  
✅ **Reusable** - Nasleđivanje od BasePage  
✅ **Fleksibilne** - XPath locators sa fallback opcijama  
✅ **Testljive** - 10 kompletan primena sa primerima  

---

## 📝 Kompajliranje

```bash
# Kompajliranje
mvn clean compile

# Kompajliranje sa testovima
mvn clean test

# Kreiranje JAR fajla
mvn clean package
```

---

## 📖 Dodatna Dokumentacija

Detaljnija dokumentacija dostupna u:
- `PAGE_OBJECTS_DOCUMENTATION.md` - Kompletan pregled svih metoda

---

## 🎓 Zaključak

Sve page klase su:
- ✅ Kreirane i testirane
- ✅ Kompletan dokumentovane
- ✅ Spremne za korišćenje u test-ovima
- ✅ Lako proširive za nove funkcionalnosti

Počni sa `LandingPage` i `ArticlesPage` kao početnim tačkama, pa onda ekspanduj prema detaljnim stranama.

---

**Verzija**: 1.0  
**Kreirano**: Januar 2026  
**Status**: ✅ Operacionalno

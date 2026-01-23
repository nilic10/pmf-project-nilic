# Page Objects Documentation

Dokumentacija svih Page Object klasa kreiranih za Articles Selenium Testove.

## Struktura

Sve page klase nalaze se u paketu: `rs.pmf.selenium.pages`

Sve klase nasleđuju od `BasePage` koja pruža osnovne Selenium metode.

---

## 1. LandingPage

**Lokacija**: `src/main/java/rs/pmf/selenium/pages/LandingPage.java` (230 linija)

**Opis**: Početna stranica sa "Let's Start" dugmeta.

### Ključne Metode:

#### Navigacija
- `void navigateToLandingPage(String baseUrl)` - Navigira na Landing Page
- `boolean isOnLandingPage()` - Proverava da li je korisnik na Landing Page

#### Sadržaj
- `String getPageTitle()` - Dobija naslov stranice
- `String getPageDescription()` - Dobija opis stranice
- `boolean isTitleVisible()` - Proverava da li je naslov vidljiv
- `boolean isDescriptionVisible()` - Proverava da li je opis vidljiv

#### Let's Start Dugme
- `void clickLetsStartButton()` - Klikće na "Let's Start" dugme
- `boolean isLetsStartButtonVisible()` - Proverava da li je dugme vidljivo
- `boolean isLetsStartButtonAvailable()` - Proverava da li je dugme dostupno
- `String getLetsStartButtonText()` - Dobija tekst dugmeta

#### Footer
- `String getFooterVersion()` - Dobija verziju iz footer-a
- `boolean isFooterVisible()` - Proverava da li je footer vidljiv
- `void clickFooterLink()` - Klikće na footer link
- `String getFooterLinkUrl()` - Dobija URL footer linka

#### Teme
- `boolean areThemeButtonsAvailable()` - Proverava dostupnost theme dugmića
- `int getThemeButtonsCount()` - Dobija broj theme dugmića
- `void clickFirstThemeButton()` - Klikće na prvi theme dugme

#### Stanja
- `void waitForLandingPageToLoad()` - Čeka da se stranica učita
- `boolean isPageFullyLoaded()` - Proverava da li je stranica kompletan učitana

---

## 2. ArticlesPage

**Lokacija**: `src/main/java/rs/pmf/selenium/pages/ArticlesPage.java` (289 linija)

**Opis**: Stranica sa listom članaka, paginacijom i sortiranjem.

### Ključne Metode:

#### Navigacija
- `void navigateToArticles()` - Navigira na Articles tab
- `void goToComments()` - Ide na Comments tab
- `boolean isArticlesTabActive()` - Proverava da li je Articles tab aktivan

#### Paginacija
- `boolean isNextButtonAvailable()` - Proverava dostupnost Next dugmeta
- `boolean isPrevButtonAvailable()` - Proverava dostupnost Prev dugmeta
- `void clickNext()` - Ide na sledeću stranicu
- `void clickPrev()` - Ide na prethodnu stranicu
- `String getPageInfo()` - Dobija info o trenutnoj stranici
- `void setItemsPerPage(String itemsPerPage)` - Postavlja broj članova po stranici

#### Sortiranje
- `void sortBy(String sortOption)` - Sortira članke po opciji (date, name, itd.)

#### Pretraga
- `void searchArticles(String keyword)` - Pretražuje članke po ključnoj reči
- `void clearSearch()` - Briše pretragu

#### Članci
- `int getArticlesCount()` - Dobija broj vidljivih članaka
- `List<String> getArticlesTitles()` - Dobija sve naslove
- `String getArticleTitle(int index)` - Dobija naslov članka na poziciji
- `String getArticleAuthor(int index)` - Dobija autora na poziciji
- `String getArticleDate(int index)` - Dobija datum na poziciji
- `String getArticleDescription(int index)` - Dobija opis na poziciji
- `void clickSeeMoreForArticle(int index)` - Klikće na "See More" za članak
- `void clickArticleTitle(int index)` - Klikće na naslov članka
- `boolean isArticleVisible(String title)` - Proverava da li je članak vidljiv

#### Stanja
- `void waitForArticlesToLoad()` - Čeka da se članci učitaju

---

## 3. CommentsPage

**Lokacija**: `src/main/java/rs/pmf/selenium/pages/CommentsPage.java` (276 linija)

**Opis**: Stranica sa listom komentara, paginacijom i sortiranjem.

### Ključne Metode:

#### Navigacija
- `void navigateToComments()` - Navigira na Comments tab
- `void goToArticles()` - Ide na Articles tab
- `boolean isCommentsTabActive()` - Proverava da li je Comments tab aktivan

#### Paginacija
- `boolean isNextButtonAvailable()` - Proverava dostupnost Next dugmeta
- `boolean isPrevButtonAvailable()` - Proverava dostupnost Prev dugmeta
- `void clickNext()` - Ide na sledeću stranicu
- `void clickPrev()` - Ide na prethodnu stranicu
- `String getPageInfo()` - Dobija info o trenutnoj stranici
- `void setItemsPerPage(String itemsPerPage)` - Postavlja broj komentara po stranici

#### Sortiranje
- `void sortBy(String sortOption)` - Sortira komentare po opciji

#### Pretraga
- `void searchComments(String keyword)` - Pretražuje komentare po ključnoj reči
- `void clearSearch()` - Briše pretragu

#### Komentari
- `int getCommentsCount()` - Dobija broj vidljivih komentara
- `String getCommentText(int index)` - Dobija tekst komentara na poziciji
- `String getCommentAuthor(int index)` - Dobija autora na poziciji
- `String getCommentDate(int index)` - Dobija datum na poziciji
- `String getCommentArticle(int index)` - Dobija članak za komentar
- `List<String> getCommentsTexts()` - Dobija sve tekstove komentara
- `void clickSeeCommentForComment(int index)` - Klikće na "See comment" za komentar
- `boolean isCommentVisible(String commentText)` - Proverava da li je komentar vidljiv

#### Stanja
- `void waitForCommentsToLoad()` - Čeka da se komentari učitaju

---

## 4. SingleArticlePage

**Lokacija**: `src/main/java/rs/pmf/selenium/pages/SingleArticlePage.java` (250 linija)

**Opis**: Stranica sa detaljima pojedinačnog članka.

### Ključne Metode:

#### Navigacija
- `boolean isOnSingleArticlePage()` - Proverava da li je na Single Article stranici
- `void returnToArticles()` - Vraća se na Articles stranicu
- `boolean isReturnLinkAvailable()` - Proverava dostupnost linka za povratak

#### Naslov i Slika
- `String getArticleTitle()` - Dobija naslov članka
- `boolean isArticleImageVisible()` - Proverava da li je slika vidljiva
- `String getArticleImageAttribute(String attributeName)` - Dobija atribut slike

#### Metadata
- `String getArticleId()` - Dobija ID članka
- `String getArticleAuthor()` - Dobija autora članka
- `String getArticleDate()` - Dobija datum objave

#### Sadržaj
- `String getArticleContent()` - Dobija kompletan tekst članka
- `boolean isContentContains(String text)` - Proverava da li sadržaj sadrži tekst
- `int getContentParagraphsCount()` - Dobija broj paragrafa

#### Download
- `boolean isDownloadSectionVisible()` - Proverava da li je download sekcija vidljiva
- `boolean isCSVDownloadAvailable()` - Proverava dostupnost CSV download-a
- `boolean isJSONDownloadAvailable()` - Proverava dostupnost JSON download-a
- `boolean isPDFDownloadAvailable()` - Proverava dostupnost PDF download-a
- `void downloadAsCSV()` - Preuzima članak kao CSV
- `void downloadAsJSON()` - Preuzima članak kao JSON
- `void downloadAsPDF()` - Preuzima članak kao PDF

#### Stanja
- `void waitForArticleToLoad()` - Čeka da se članak učita
- `boolean isArticleFullyLoaded()` - Proverava da li je članak kompletan učitan
- `void scrollToContent()` - Skroluje do sadržaja

---

## 5. SingleCommentPage

**Lokacija**: `src/main/java/rs/pmf/selenium/pages/SingleCommentPage.java` (227 linija)

**Opis**: Stranica sa detaljima pojedinačnog komentara.

### Ključne Metode:

#### Navigacija
- `boolean isOnSingleCommentPage()` - Proverava da li je na Single Comment stranici
- `void returnToComments()` - Vraća se na Comments stranicu
- `boolean isReturnToCommentsLinkAvailable()` - Proverava dostupnost linka
- `void returnToArticle()` - Vraća se na članak
- `boolean isReturnToArticleLinkAvailable()` - Proverava dostupnost linka na članak

#### Metadata
- `String getCommentId()` - Dobija ID komentara
- `String getCommentAuthor()` - Dobija autora komentara
- `String getCommentDate()` - Dobija datum komentara

#### Tekst Komentara
- `String getCommentText()` - Dobija tekst komentara
- `boolean isCommentTextContains(String text)` - Proverava da li tekst sadrži tekst
- `int getCommentTextLength()` - Dobija dužinu teksta (broj karaktera)

#### Članak
- `String getArticleTitle()` - Dobija naslov članka na kojem je komentar
- `boolean isArticleInfoVisible()` - Proverava da li je info o članku vidljiva
- `void clickArticleLink()` - Klikće na link članka
- `boolean isArticleLinkAvailable()` - Proverava dostupnost linka na članak

#### Stanja
- `void waitForCommentToLoad()` - Čeka da se komentar učita
- `boolean isCommentFullyLoaded()` - Proverava da li je komentar kompletan učitan
- `void scrollToCommentText()` - Skroluje do teksta komentara
- `boolean isAuthorLink()` - Proverava da li je autor link

---

## Zajedničke Metode (Nasleđene od BasePage)

Sve page klase nasleđuju sledeće korisne metode:

### Navigacija
- `void navigateTo(String url)` - Otvara URL
- `void refreshPage()` - Osvežava stranicu
- `void goBack()` - Ide na prethodnu stranicu

### Pronalaženje Elemenata
- `WebElement findElement(By locator)` - Pronalazi element
- `List<WebElement> findElements(By locator)` - Pronalazi sve elemente

### Interakcija sa Elementima
- `void click(By locator)` - Klikće na element
- `void sendKeys(By locator, String text)` - Unosi tekst
- `String getText(By locator)` - Dobija tekst
- `String getAttribute(By locator, String attributeName)` - Dobija atribut
- `void clearField(By locator)` - Briše tekst

### Čekanja
- `void waitForElementToBeVisible(By locator)` - Čeka da element bude vidljiv
- `void waitForElementToBeClickable(By locator)` - Čeka da element bude klikljiv
- `void waitForElementToDisappear(By locator)` - Čeka da element nestane
- `void waitForPageLoad()` - Čeka da se stranica učita

### JavaScript Operacije
- `void scrollToElement(By locator)` - Skroluje do elementa
- `void scrollToTop()` - Skroluje na vrh
- `void scrollToBottom()` - Skroluje na dno

### Dropdown Operacije
- `void selectByVisibleText(By locator, String text)` - Bira opciju po tekstu
- `void selectByValue(By locator, String value)` - Bira opciju po value atributu
- `void selectByIndex(By locator, int index)` - Bira opciju po index-u

---

## Primer Upotrebe

```java
import rs.pmf.selenium.pages.*;

// Kreiranje page objekata
LandingPage landingPage = new LandingPage(driver);
ArticlesPage articlesPage = new ArticlesPage(driver);
SingleArticlePage singleArticlePage = new SingleArticlePage(driver);

// Korišćenje page objekata
landingPage.navigateToLandingPage("http://localhost:3000/");
assertTrue(landingPage.isPageFullyLoaded());

landingPage.clickLetsStartButton();

assertTrue(articlesPage.isArticlesTabActive());
int articlesCount = articlesPage.getArticlesCount();
System.out.println("Broj članaka: " + articlesCount);

articlesPage.clickArticleTitle(0);
assertTrue(singleArticlePage.isOnSingleArticlePage());

String title = singleArticlePage.getArticleTitle();
String author = singleArticlePage.getArticleAuthor();
String content = singleArticlePage.getArticleContent();
```

---

## Statistika

| Page Klasa | Broj Linija | Broj Metoda |
|-----------|------------|-----------|
| LandingPage | 230 | ~20 |
| ArticlesPage | 289 | ~25 |
| CommentsPage | 276 | ~25 |
| SingleArticlePage | 250 | ~20 |
| SingleCommentPage | 227 | ~20 |
| **UKUPNO** | **1272** | **~110** |

---

## Pregledi Lokacija

```
src/main/java/rs/pmf/selenium/pages/
├── LandingPage.java (230 linija)
├── ArticlesPage.java (289 linija)
├── CommentsPage.java (276 linija)
├── SingleArticlePage.java (250 linija)
└── SingleCommentPage.java (227 linija)
```

---

## Napomene za Testove

1. **BasePage Logging**: Sve page klase koriste SLF4J logger koji automatski loguje sve akcije.
2. **Explicit Waits**: Sve metode koriste eksplicitno čekanje za robusnost.
3. **Reusable Locators**: Svi locatori su definisani kao private static final za bolje održavanje.
4. **Error Handling**: Sve metode imaju odgovarajuće error handlinge sa descriptivnim porukama.

---

**Kreirano**: Januar 2026

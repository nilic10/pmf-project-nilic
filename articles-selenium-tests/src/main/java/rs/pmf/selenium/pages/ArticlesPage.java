package rs.pmf.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rs.pmf.selenium.base.BasePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object klasa za stranicu sa listom članaka (Articles)
 */
public class ArticlesPage extends BasePage {
    
    // ==================== LOCATORS ====================
    
    // Navigacijski elementi
    private static final By ARTICLES_TAB = By.xpath("//button[contains(text(), 'Articles')]");
    private static final By COMMENTS_TAB = By.xpath("//button[contains(text(), 'Comments')]");
    
    // Paginacija i sortiranje
    private static final By PREV_BUTTON = By.xpath("//a[contains(text(), 'Prev')]");
    private static final By NEXT_BUTTON = By.xpath("//a[contains(text(), 'Next')]");
    private static final By PAGE_INFO = By.xpath("//*[contains(text(), 'Page:')]");
    private static final By ITEMS_PER_PAGE_SELECT = By.xpath("//select[contains(@class, 'items-per-page')]");
    private static final By SORT_SELECT = By.xpath("//select[contains(@class, 'sort')]");
    private static final By SEARCH_INPUT = By.xpath("//input[contains(@placeholder, 'Search')]");
    private static final By GO_BUTTON = By.xpath("//button[contains(text(), 'GO')]");
    
    // Članci
    private static final By ARTICLES_CONTAINER = By.xpath("//div[contains(@class, 'articles')]");
    private static final By ARTICLE_CARDS = By.xpath("//div[contains(@class, 'article-card') or contains(@class, 'article')]");
    private static final By ARTICLE_TITLE = By.xpath(".//a[contains(@class, 'article-title')]");
    private static final By ARTICLE_AUTHOR = By.xpath(".//*[contains(text(), 'user:')]/following-sibling::a");
    private static final By ARTICLE_DATE = By.xpath(".//*[contains(text(), 'date:')]/following-sibling::*");
    private static final By ARTICLE_DESCRIPTION = By.xpath(".//p[contains(@class, 'description')]");
    private static final By SEE_MORE_LINK = By.xpath(".//a[contains(text(), 'See More')]");
    
    /**
     * Konstruktor
     */
    public ArticlesPage(WebDriver driver) {
        super(driver);
    }
    
    /**
     * Klikće na Comments tab
     */
    public CommentsPage goToComments() {
        logger.info("Navigacija na Comments tab");
        click(COMMENTS_TAB);
        waitForPageLoad();
        return new CommentsPage(driver);
    }
    
    // ==================== PAGINACIJA ====================
    
    /**
     * Proverava da li je Next dugme dostupno
     */
    public boolean isNextButtonAvailable() {
        logger.debug("Provera dostupnosti Next dugmeta");
        return isElementPresent(NEXT_BUTTON);
    }
    
    /**
     * Proverava da li je Prev dugme dostupno
     */
    public boolean isPrevButtonAvailable() {
        logger.debug("Provera dostupnosti Prev dugmeta");
        return isElementPresent(PREV_BUTTON);
    }
    
    /**
     * Klikće na Next dugme za sledećinu stranicu
     */
    public ArticlesPage clickNext() {
        logger.info("Klik na Next dugme");
        click(NEXT_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Klikće na Prev dugme za prethodnu stranicu
     */
    public ArticlesPage clickPrev() {
        logger.info("Klik na Prev dugme");
        click(PREV_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Dobija informaciju o trenutnoj stranici
     */
    public String getPageInfo() {
        logger.debug("Preuzimanje informacije o stranici");
        return getText(PAGE_INFO);
    }
    
    /**
     * Postavlja broj članka po stranici
     */
    public ArticlesPage setItemsPerPage(String itemsPerPage) {
        logger.info("Postavljanje broja članova po stranici: {}", itemsPerPage);
        selectByVisibleText(ITEMS_PER_PAGE_SELECT, itemsPerPage);
        waitForPageLoad();
        return this;
    }
    
    // ==================== SORTIRANJE ====================
    
    /**
     * Sortira članke po datumu ili imenu
     */
    public ArticlesPage sortBy(String sortOption) {
        logger.info("Sortiranje po: {}", sortOption);
        selectByVisibleText(SORT_SELECT, sortOption);
        waitForPageLoad();
        return this;
    }
    
    // ==================== PRETRAGA ====================
    
    /**
     * Pretražuje članke po ključnoj reči
     */
    public ArticlesPage searchArticles(String keyword) {
        logger.info("Pretraga članaka sa ključnom reči: {}", keyword);
        sendKeys(SEARCH_INPUT, keyword);
        click(GO_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Briše pretragu
     */
    public ArticlesPage clearSearch() {
        logger.info("Brisanje pretrage");
        clearField(SEARCH_INPUT);
        click(GO_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    // ==================== ČLANCI ====================
    
    /**
     * Dobija broj svih vidljivih članaka na stranici
     */
    public int getArticlesCount() {
        logger.debug("Preuzimanje broja članaka na stranici");
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        logger.info("Broj članaka na stranici: {}", articles.size());
        return articles.size();
    }
    
    /**
     * Dobija listu naslova svih članaka na stranici
     */
    public List<String> getArticlesTitles() {
        logger.debug("Preuzimanje naslova svih članaka");
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        List<String> titles = new java.util.ArrayList<>();
        
        for (WebElement article : articles) {
            try {
                String title = article.findElement(ARTICLE_TITLE).getText();
                titles.add(title);
            } catch (Exception e) {
                logger.warn("Greška pri preuzimanju naslova članka", e);
            }
        }
        logger.info("Preuzeto {} naslova", titles.size());
        return titles;
    }
    
    /**
     * Dobija naslov članka na određenoj poziciji
     */
    public String getArticleTitle(int index) {
        logger.debug("Preuzimanje naslova članka na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            logger.error("Indeks {} je van granica", index);
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return articles.get(index).findElement(ARTICLE_TITLE).getText();
    }
    
    /**
     * Dobija autora članka na određenoj poziciji
     */
    public String getArticleAuthor(int index) {
        logger.debug("Preuzimanje autora članka na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return articles.get(index).findElement(ARTICLE_AUTHOR).getText();
    }
    
    /**
     * Dobija datum članka na određenoj poziciji
     */
    public String getArticleDate(int index) {
        logger.debug("Preuzimanje datuma članka na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return articles.get(index).findElement(ARTICLE_DATE).getText();
    }
    
    /**
     * Dobija kratko opisanu članaka na određenoj poziciji
     */
    public String getArticleDescription(int index) {
        logger.debug("Preuzimanje opisa članka na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return articles.get(index).findElement(ARTICLE_DESCRIPTION).getText();
    }
    
    /**
     * Klikće na "See More" link određenog članka
     */
    public SingleArticlePage clickSeeMoreForArticle(int index) {
        logger.info("Klik na 'See More' za članak na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        articles.get(index).findElement(SEE_MORE_LINK).click();
        waitForPageLoad();
        return new SingleArticlePage(driver);
    }
    
    /**
     * Klikće na naslov članka da otvori detalje
     */
    public SingleArticlePage clickArticleTitle(int index) {
        logger.info("Klik na naslov članka na poziciji: {}", index);
        List<WebElement> articles = findElements(ARTICLE_CARDS);
        if (index >= articles.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        articles.get(index).findElement(ARTICLE_TITLE).click();
        waitForPageLoad();
        return new SingleArticlePage(driver);
    }
    
    /**
     * Proverava da li je člának sa datim naslovom vidljiv
     */
    public boolean isArticleVisible(String title) {
        logger.debug("Provera vidljivosti članka sa naslovom: {}", title);
        List<String> titles = getArticlesTitles();
        boolean isVisible = titles.contains(title);
        logger.info("Članak '{}' je {}vidljiv", title, isVisible ? "" : "ni");
        return isVisible;
    }
    
    // ==================== STANJA ====================
    
    /**
     * Proverava da li je Articles tab aktivan
     */
    public boolean isArticlesTabActive() {
        logger.debug("Provera da li je Articles tab aktivan");
        String className = getAttribute(ARTICLES_TAB, "class");
        boolean isActive = className != null && className.contains("active");
        logger.info("Articles tab je {}aktivan", isActive ? "" : "ni");
        return isActive;
    }
    
    /**
     * Čeka da se članci učitaju
     */
    public ArticlesPage waitForArticlesToLoad() {
        logger.info("Čekanje da se članci učitaju");
        waitForElementToBeVisible(ARTICLE_CARDS);
        return this;
    }
    
    /**
     * Verifikacija: Articles tab je aktivan (asertacija)
     */
    public ArticlesPage verifyArticlesTabActive() {
        logger.info("Verifikacija da je Articles tab aktivan");
        assertTrue(isArticlesTabActive(), "Articles tab nije aktivan.");
        return this;
    }
    
    /**
     * Verifikacija: postoji bar 1 članak (asertacija)
     */
    public ArticlesPage verifyHasAnyArticles() {
        logger.info("Verifikacija da postoji bar jedan članak");
        assertTrue(getArticlesCount() > 0, "Očekivano je da postoji bar jedan članak, ali lista je prazna.");
        return this;
    }
}

package rs.pmf.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import rs.pmf.selenium.base.BasePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Page Object klasa za stranicu sa listom komentara (Comments)
 */
public class CommentsPage extends BasePage {
    
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
    
    // Komentari
    private static final By COMMENTS_CONTAINER = By.xpath("//div[contains(@class, 'comments')]");
    private static final By COMMENT_CARDS = By.xpath("//div[contains(@class, 'comment-card') or contains(@class, 'comment')]");
    private static final By COMMENT_ARTICLE = By.xpath(".//*[contains(text(), 'article:')]/following-sibling::a");
    private static final By COMMENT_AUTHOR = By.xpath(".//*[contains(text(), 'user:')]/following-sibling::a");
    private static final By COMMENT_DATE = By.xpath(".//*[contains(text(), 'date:')]/following-sibling::*");
    private static final By COMMENT_TEXT = By.xpath(".//p[contains(@class, 'comment-text')]");
    private static final By SEE_COMMENT_LINK = By.xpath(".//a[contains(text(), 'See comment')]");
    
    /**
     * Konstruktor
     */
    public CommentsPage(WebDriver driver) {
        super(driver);
    }
    
    // ==================== NAVIGATION ====================
    
    /**
     * Navigira na Comments stranicu
     */
    public CommentsPage navigateToComments() {
        logger.info("Navigacija na Comments stranicu");
        click(COMMENTS_TAB);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Klikće na Articles tab
     */
    public ArticlesPage goToArticles() {
        logger.info("Navigacija na Articles tab");
        click(ARTICLES_TAB);
        waitForPageLoad();
        return new ArticlesPage(driver);
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
    public CommentsPage clickNext() {
        logger.info("Klik na Next dugme");
        click(NEXT_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Klikće na Prev dugme za prethodnu stranicu
     */
    public CommentsPage clickPrev() {
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
     * Postavlja broj komentara po stranici
     */
    public CommentsPage setItemsPerPage(String itemsPerPage) {
        logger.info("Postavljanje broja komentara po stranici: {}", itemsPerPage);
        selectByVisibleText(ITEMS_PER_PAGE_SELECT, itemsPerPage);
        waitForPageLoad();
        return this;
    }
    
    // ==================== SORTIRANJE ====================
    
    /**
     * Sortira komentare po datumu ili imenu
     */
    public CommentsPage sortBy(String sortOption) {
        logger.info("Sortiranje po: {}", sortOption);
        selectByVisibleText(SORT_SELECT, sortOption);
        waitForPageLoad();
        return this;
    }
    
    // ==================== PRETRAGA ====================
    
    /**
     * Pretražuje komentare po ključnoj reči
     */
    public CommentsPage searchComments(String keyword) {
        logger.info("Pretraga komentara sa ključnom reči: {}", keyword);
        sendKeys(SEARCH_INPUT, keyword);
        click(GO_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Briše pretragu
     */
    public CommentsPage clearSearch() {
        logger.info("Brisanje pretrage");
        clearField(SEARCH_INPUT);
        click(GO_BUTTON);
        waitForPageLoad();
        return this;
    }
    
    // ==================== KOMENTARI ====================
    
    /**
     * Dobija broj svih vidljivih komentara na stranici
     */
    public int getCommentsCount() {
        logger.debug("Preuzimanje broja komentara na stranici");
        List<WebElement> comments = findElements(COMMENT_CARDS);
        logger.info("Broj komentara na stranici: {}", comments.size());
        return comments.size();
    }
    
    /**
     * Dobija tekst komentara na određenoj poziciji
     */
    public String getCommentText(int index) {
        logger.debug("Preuzimanje teksta komentara na poziciji: {}", index);
        List<WebElement> comments = findElements(COMMENT_CARDS);
        if (index >= comments.size()) {
            logger.error("Indeks {} je van granica", index);
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return comments.get(index).findElement(COMMENT_TEXT).getText();
    }
    
    /**
     * Dobija autora komentara na određenoj poziciji
     */
    public String getCommentAuthor(int index) {
        logger.debug("Preuzimanje autora komentara na poziciji: {}", index);
        List<WebElement> comments = findElements(COMMENT_CARDS);
        if (index >= comments.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return comments.get(index).findElement(COMMENT_AUTHOR).getText();
    }
    
    /**
     * Dobija datum komentara na određenoj poziciji
     */
    public String getCommentDate(int index) {
        logger.debug("Preuzimanje datuma komentara na poziciji: {}", index);
        List<WebElement> comments = findElements(COMMENT_CARDS);
        if (index >= comments.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return comments.get(index).findElement(COMMENT_DATE).getText();
    }
    
    /**
     * Dobija članak na kojem je komentar na određenoj poziciji
     */
    public String getCommentArticle(int index) {
        logger.debug("Preuzimanje članka za komentar na poziciji: {}", index);
        List<WebElement> comments = findElements(COMMENT_CARDS);
        if (index >= comments.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        return comments.get(index).findElement(COMMENT_ARTICLE).getText();
    }
    
    /**
     * Dobija listu tekstova svih komentara na stranici
     */
    public List<String> getCommentsTexts() {
        logger.debug("Preuzimanje tekstova svih komentara");
        List<WebElement> comments = findElements(COMMENT_CARDS);
        List<String> texts = new java.util.ArrayList<>();
        
        for (WebElement comment : comments) {
            try {
                String text = comment.findElement(COMMENT_TEXT).getText();
                texts.add(text);
            } catch (Exception e) {
                logger.warn("Greška pri preuzimanju teksta komentara", e);
            }
        }
        logger.info("Preuzeto {} komentara", texts.size());
        return texts;
    }
    
    /**
     * Klikće na "See comment" link određenog komentara
     */
    public SingleCommentPage clickSeeCommentForComment(int index) {
        logger.info("Klik na 'See comment' za komentar na poziciji: {}", index);
        List<WebElement> comments = findElements(COMMENT_CARDS);
        if (index >= comments.size()) {
            throw new IndexOutOfBoundsException("Indeks " + index + " je van granica");
        }
        comments.get(index).findElement(SEE_COMMENT_LINK).click();
        waitForPageLoad();
        return new SingleCommentPage(driver);
    }
    
    /**
     * Proverava da li je komentar sa datim tekstom vidljiv
     */
    public boolean isCommentVisible(String commentText) {
        logger.debug("Provera vidljivosti komentara sa tekstom: {}", commentText);
        List<String> texts = getCommentsTexts();
        boolean isVisible = texts.stream().anyMatch(text -> text.contains(commentText));
        logger.info("Komentar '{}' je {}vidljiv", commentText, isVisible ? "" : "ni");
        return isVisible;
    }
    
    // ==================== STANJA ====================
    
    /**
     * Proverava da li je Comments tab aktivan
     */
    public boolean isCommentsTabActive() {
        logger.debug("Provera da li je Comments tab aktivan");
        String className = getAttribute(COMMENTS_TAB, "class");
        boolean isActive = className != null && className.contains("active");
        logger.info("Comments tab je {}aktivan", isActive ? "" : "ni");
        return isActive;
    }
    
    /**
     * Čeka da se komentari učitaju
     */
    public CommentsPage waitForCommentsToLoad() {
        logger.info("Čekanje da se komentari učitaju");
        waitForElementToBeVisible(COMMENT_CARDS);
        return this;
    }

    /**
     * Verifikacija: Comments tab je aktivan (asertacija)
     */
    public CommentsPage verifyCommentsTabActive() {
        logger.info("Verifikacija da je Comments tab aktivan");
        assertTrue(isCommentsTabActive(), "Comments tab nije aktivan.");
        return this;
    }

    /**
     * Verifikacija: ima bar jedan komentar (asertacija)
     */
    public CommentsPage verifyHasAnyComments() {
        logger.info("Verifikacija da postoji bar jedan komentar");
        assertTrue(getCommentsCount() > 0, "Očekivano je da postoji bar jedan komentar, ali lista je prazna.");
        return this;
    }

    /**
     * Verifikacija: komentar sa datim tekstom je vidljiv (asertacija)
     */
    public CommentsPage verifyCommentVisible(String commentText) {
        logger.info("Verifikacija vidljivosti komentara sa tekstom: {}", commentText);
        assertTrue(isCommentVisible(commentText), "Komentar koji sadrži tekst '" + commentText + "' nije vidljiv.");
        return this;
    }
}

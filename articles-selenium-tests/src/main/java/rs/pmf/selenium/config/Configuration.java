package rs.pmf.selenium.config;

/**
 * Klasa za skladištenje konfiguracije testova
 */
public class Configuration {
    
    public static final String BASE_URL = "http://localhost:3000";
    public static final String ARTICLES_PAGE_URL = BASE_URL + "/articles.html";
    
    // Waits
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 15;
    public static final int PAGE_LOAD_TIMEOUT = 20;
    
    // Browser
    private static BrowserType browserType = BrowserType.CHROME;
    private static String gridUrl = "http://localhost:4444";
    
    // Getters
    public static BrowserType getBrowserType() {
        String browser = System.getProperty("browser", "chrome");
        browserType = BrowserType.fromString(browser);
        return browserType;
    }
    
    public static void setBrowserType(BrowserType type) {
        browserType = type;
    }
    
    public static String getGridUrl() {
        return System.getProperty("gridUrl", gridUrl);
    }
    
    public static void setGridUrl(String url) {
        gridUrl = url;
    }
}

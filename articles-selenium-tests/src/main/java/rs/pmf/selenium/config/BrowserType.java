package rs.pmf.selenium.config;

/**
 * Enum za različite tipove browsera koji se koriste za testiranje
 */
public enum BrowserType {
    CHROME("chrome"),
    FIREFOX("firefox"),
    REMOTE_CHROME("remote_chrome"),
    REMOTE_FIREFOX("remote_firefox");

    private final String value;

    BrowserType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static BrowserType fromString(String value) {
        for (BrowserType browser : BrowserType.values()) {
            if (browser.value.equalsIgnoreCase(value)) {
                return browser;
            }
        }
        return CHROME; // Default
    }
}

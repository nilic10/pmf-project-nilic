package com.example.selenium.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Factory class for creating WebDriver instances.
 * Supports Chrome, Firefox, and remote execution via Selenium Grid.
 */
public class DriverFactory {

    /**
     * Creates and returns a WebDriver instance for the specified browser.
     * Supports "chrome", "firefox", "remote-chrome", and "remote-firefox".
     * Remote instances use the URL provided by the 'gridUrl' system property (default: http://localhost:4444/wd/hub).
     *
     * <p>Proxy configuration can be provided via the 'proxy' system property (e.g., -Dproxy=localhost:8080).
     * Supported for Chrome and Firefox (both local and remote).</p>
     *
     * @param browser The browser type to initialize.
     * @return Initialized WebDriver instance, maximized.
     * @throws IllegalArgumentException If the browser type is not supported.
     * @throws RuntimeException         If there is a MalformedURLException for the Grid URL.
     */
    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        String gridUrl = System.getProperty("gridUrl", "http://localhost:4444/wd/hub");
        String proxyServer = System.getProperty("proxy");
        String browserMobProxy = System.getProperty("browserMobProxy");

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                if (proxyServer != null) {
                    options.addArguments("--proxy-bypass-list=<-loopback>");
                    options.addArguments("--proxy-server=" + proxyServer);
                }
                if (browserMobProxy != null) {
                    // start browser mob proxy
                    Proxy seleniumProxy = BrowserMobProxyServiceCreator
                            .getInstance().startBrowserMobProxy();

                    // add proxy to options
                    String proxyString = String.format("localhost:%d",
                             BrowserMobProxyServiceCreator
                                    .getInstance().getPort());
                    seleniumProxy.setProxyType(Proxy.ProxyType.MANUAL)
                            .setHttpProxy(proxyString).setSslProxy(proxyString);

                    BrowserMobProxyServiceCreator.getInstance()
                            .enableHarCaptureTypes();
                    options.setProxy(seleniumProxy);
                    options.addArguments("--ignore-certificate-errors");
                    // Chrome bypasses the proxy for loopback addresses (localhost/127.0.0.1) by default,
                    // which would exclude the app under test from the HAR capture.
                    options.addArguments("--proxy-bypass-list=<-loopback>");
                }
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (proxyServer != null) {
                    Proxy proxy = new Proxy();
                    proxy.setHttpProxy(proxyServer);
                    proxy.setSslProxy(proxyServer);
                    ffOptions.setProxy(proxy);
                }
                driver = new FirefoxDriver(ffOptions);
                break;
            case "remote-chrome":
                ChromeOptions remoteChromeOptions = new ChromeOptions();
                if (proxyServer != null) {
                    remoteChromeOptions.addArguments("--proxy-bypass-list=<-loopback>"); // needs for including localhost domain
                    remoteChromeOptions.addArguments("--proxy-server=" + proxyServer); //$NON-NLS-1$
                }
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), remoteChromeOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL", e);
                }
                break;
            case "remote-firefox":
                FirefoxOptions remoteFfOptions = new FirefoxOptions();
                if (proxyServer != null) {
                    Proxy proxy = new Proxy();
                    proxy.setHttpProxy(proxyServer);
                    proxy.setSslProxy(proxyServer);
                    remoteFfOptions.setProxy(proxy);
                }
                try {
                    driver = new RemoteWebDriver(new URL(gridUrl), remoteFfOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException("Invalid Grid URL", e);
                }
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().window().maximize();
        return driver;
    }
}

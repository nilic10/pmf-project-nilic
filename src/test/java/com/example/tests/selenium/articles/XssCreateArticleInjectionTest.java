package com.example.tests.selenium.articles;

import com.example.rest.RestClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test suite for verifying that the article form is not vulnerable to XSS injection.
 * Creates a test user via REST API and attempts to inject script tags through the UI.
 */
public class XssCreateArticleInjectionTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private final String articleTitle = "Automated Article " + UUID.randomUUID().toString().substring(0, 5);
    private static final String articleContent = "This is a content for the automated article.";

    /**
     * Creates a unique test user via REST API before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "article_creator_" + uniqueSuffix + "@example.com";

        User testUser = User.builder()
                .email(email)
                .firstname("Article")
                .lastname("Creator")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();

        RestClient restClient = new RestClient();
        restClient.createUser(testUser);
    }

    /**
     * Tests that injecting a script tag into the article title field does not result in script execution.
     * Verifies that the raw payload is not rendered as executable HTML in the page source.
     */
    @Test
    public void injectingScriptTagIntoArticleTitleShouldNotBeExecuted() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(email, password)
                .goToArticles()
                .addArticle()
                .enterTitle("<script>alert('XSS')</script>")
                .enterContent("Test sadržaj članka")
                .clickSave()
                .verifySuccessMessage("Article created!")
                .verifyArticleWithTitleExists("<script>alert('XSS')</script>");

        boolean payloadRenderedAsScript =
                driver.getPageSource().contains("<script>alert('XSS')</script>");

        assertFalse(payloadRenderedAsScript,
                "Article title field is vulnerable to XSS attack");
    }


}

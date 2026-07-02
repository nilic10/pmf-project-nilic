package com.example.tests.selenium.articles;

import com.example.rest.RestClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test suite for creating articles via the Selenium UI.
 * Creates a test user via REST API and verifies article creation through the browser interface.
 */
public class CreateArticleTest extends BaseTest {

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
     * Tests that a new article can be created through the UI and appears in the articles list.
     * Verifies the success message and that the article title is visible after creation.
     */
    @Test
    @DisplayName("Create a new article via Selenium")
    public void testCreateArticle() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToArticles()
                .addArticle()
                .enterTitle(articleTitle)
                .enterContent(articleContent)
                .clickSave()
                .verifySuccessMessage("Article created!")
                .verifyArticleWithTitleExists(articleTitle);
    }
}

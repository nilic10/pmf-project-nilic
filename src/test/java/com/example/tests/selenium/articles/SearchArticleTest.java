package com.example.tests.selenium.articles;

import com.example.rest.RestClient;
import com.example.rest.models.Article;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test suite for searching articles via the Selenium UI.
 * Creates a test user and article via REST API, then verifies the search functionality in the browser.
 */
public class SearchArticleTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User newUser;
    private UserClient testClient;
    private String articleTitle;

    /**
     * Creates a unique test user, logs in via REST API, and creates a searchable article before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "article_searcher_" + uniqueSuffix + "@example.com";
        articleTitle = "Searchable Article " + uniqueSuffix;

        newUser = User.builder()
                .email(email)
                .firstname("Article")
                .lastname("Searcher")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();

        RestClient restClient = new RestClient();

        testClient = restClient.createUser(newUser);

        String token = restClient.login(email, password);

        Article newArticle = Article.builder()
                .title(articleTitle)
                .body("Content for search test")
                .date("2024-05-20T10:00:00Z")
                .image("https://example.com/image.jpg")
                .user_id(testClient.getData().getId())
                .build();

        restClient.createArticle(token, newArticle);
    }

    /**
     * Tests that an article can be found using the search functionality.
     * Verifies that the searched article title appears in the results.
     */
    @Test
    @DisplayName("Search for an article via Selenium")
    public void testSearchArticle() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToArticles()
                .searchFor(articleTitle)
                .verifyArticleWithTitleExists(articleTitle);
        
    }
}

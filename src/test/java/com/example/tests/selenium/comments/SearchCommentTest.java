package com.example.tests.selenium.comments;

import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test suite for searching comments via the Selenium UI.
 * Creates a test user via REST API and verifies the comment search functionality.
 */
public class SearchCommentTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User testUser;

    /**
     * Creates a unique test user via REST API before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "comment_searcher_" + uniqueSuffix + "@example.com";
        
        testUser = User.builder()
                .email(email)
                .firstname("Comment")
                .lastname("Searcher")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();
        
        UserClient userClient = new UserClient();
        userClient.create(testUser);
    }

    /**
     * Tests that the comments search functionality works and the comments page is displayed.
     */
    @Test
    @DisplayName("Search for a comment via Selenium")
    public void testSearchComment() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToComments()
                .searchFor("test")
                .verifyCommentsPageIsDisplayed();
    }
}

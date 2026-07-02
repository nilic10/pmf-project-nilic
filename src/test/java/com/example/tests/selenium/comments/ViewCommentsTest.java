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
 * Test suite for viewing the comments page via Selenium.
 * Creates a test user via REST API and verifies the comments page is accessible.
 */
public class ViewCommentsTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User testUser;

    /**
     * Creates a unique test user via REST API before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "comment_viewer_" + uniqueSuffix + "@example.com";
        
        testUser = User.builder()
                .email(email)
                .firstname("Comment")
                .lastname("Viewer")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();
        
        UserClient userClient = new UserClient();
        userClient.create(testUser);
    }

    /**
     * Tests that the comments page is displayed after navigating to it.
     */
    @Test
    @DisplayName("View comments page")
    public void testViewComments() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToComments()
                .verifyCommentsPageIsDisplayed();
    }
}

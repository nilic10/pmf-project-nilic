package com.example.tests.selenium.login;

import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 * Test suite for verifying logout functionality via Selenium.
 * Creates a test user via REST API, logs in, and verifies the logout process.
 */
public class LogoutTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User testUser;

    /**
     * Creates a unique test user via REST API before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "logout_user_" + uniqueSuffix + "@example.com";
        
        testUser = User.builder()
                .email(email)
                .firstname("Logout")
                .lastname("User")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();
        
        UserClient userClient = new UserClient();
        userClient.create(testUser);
    }

    /**
     * Tests that a logged-in user can successfully log out and is redirected to the login page.
     */
    @Test
    @DisplayName("Logout from the application")
    public void testLogout() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .logout()
                .verifyLoginPageIsDisplayed();
    }
}

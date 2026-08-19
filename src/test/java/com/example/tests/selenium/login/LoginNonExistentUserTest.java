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
 * Test suite for verifying login behavior with a non-existent user via Selenium.
 * Verifies that attempting to log in with an unregistered email displays an error.
 */
public class LoginNonExistentUserTest extends BaseTest {

    private final String password = "password123!";

    /**
     * Sets up a non-existent user account in the database before each test.
     */
    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        String email = "non_existent_" + uniqueSuffix + "@example.com";

        User testUser = User.builder()
                .email(email)
                .firstname("Non")
                .lastname("Existent")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();

        UserClient userClient = new UserClient();
        userClient.create(testUser);
    }

    /**
     * Tests that logging in with a non-existent user email displays an error message.
     */
    @Test
    @DisplayName("Login with non-existent user")
    public void testLoginWithNonExistentUser() {
        openApp();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithError("does_not_exist@example.com", password);
    }
}

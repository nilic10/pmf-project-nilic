package com.example.tests.rest.users;

import com.example.rest.RestClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Epic("REST API Tests")
@Feature("User Management")
/**
 * Test suite for creating users via REST API.
 * Verifies that a new user account can be successfully created with valid data.
 */
public class CreateUserTest extends RestClient {

    private static final String USER_FIRSTNAME = "John";
    private static final String USER_LASTNAME = "Doe";
    private static final String USER_PASSWORD = "password123";
    private static final String USER_AVATAR = "https://example.com/avatar.jpg";

    /**
     * Tests that a new user can be created with valid data.
     * Verifies the email, first name, and last name of the created user.
     */
    @Test
    @DisplayName("Create new user - Positive Test")
    public void createUserTest() {
        String uniqueEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
        User newUser = User.builder()
                .email(uniqueEmail)
                .firstname(USER_FIRSTNAME)
                .lastname(USER_LASTNAME)
                .password(USER_PASSWORD)
                .avatar(USER_AVATAR)
                .build();

        createUser(newUser)
                .verifyEmail(uniqueEmail)
                .verifyFirstname(USER_FIRSTNAME)
                .verifyLastname(USER_LASTNAME);
    }
}

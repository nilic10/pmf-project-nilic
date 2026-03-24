package com.example.tests.rest.users;

import com.example.rest.RestClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Epic("REST API Tests")
@Feature("User Management")
public class CreateUserTest extends RestClient {

    private static final String USER_FIRSTNAME = "John";
    private static final String USER_LASTNAME = "Doe";
    private static final String USER_PASSWORD = "password123";
    private static final String USER_AVATAR = "https://example.com/avatar.jpg";

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

        UserClient userClient = new UserClient();
        userClient.setData(newUser);

        createUser(userClient)
                .verifyEmail(uniqueEmail)
                .verifyFirstname(USER_FIRSTNAME)
                .verifyLastname(USER_LASTNAME);
    }
}

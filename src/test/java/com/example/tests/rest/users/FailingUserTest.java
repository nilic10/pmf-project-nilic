package com.example.tests.rest.users;

import com.example.rest.RestClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
public class FailingUserTest extends RestClient {

    @Test
    @DisplayName("Failing User Test - Validation Error (Missing Email)")
    public void failingUserTest() {
        UserClient userClient = new UserClient();
        userClient.setData(User.builder()
                .firstname("Test")
                .lastname("User")
                .password("password")
               .build());
        userClient.create();
    }
}

package com.example.tests.rest.users;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
public class GetAllUsersTest extends RestClient {

    private static final Object USER_ID_1 = 1;
    private static final String USERNAME_1 = "Moses";
    private static final Object USER_ID_2 = 2;
    private static final String USERNAME_2 = "Danial";

    @Test
    @DisplayName("Get all users - Positive Test")
    public void getAllUsersTest() {
        getAllUsers()
                .verifyUsernameById(USER_ID_1, USERNAME_1)
                .verifyUsernameById(USER_ID_2, USERNAME_2);
    }
}

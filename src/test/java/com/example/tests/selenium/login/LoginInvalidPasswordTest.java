package com.example.tests.selenium.login;

import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class LoginInvalidPasswordTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User testUser;

    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "wrong_pass_" + uniqueSuffix + "@example.com";
        
        testUser = User.builder()
                .email(email)
                .firstname("Wrong")
                .lastname("Pass")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();
        
        UserClient userClient = new UserClient();
        userClient.setData(testUser);
        testUser = userClient.create().getData();
    }

    @Test
    @DisplayName("Login with invalid password")
    public void testLoginWithInvalidPassword() {
        driver.get("http://localhost:3000/login");
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithError(email, "wrong_password");
        
    }
}

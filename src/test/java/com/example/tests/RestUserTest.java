package com.example.tests;

import com.example.rest.BaseRest;
import com.example.rest.RestClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.common.users.UsersClient;
import org.junit.jupiter.api.Test;

public class RestUserTest extends RestClient {

    @Test
    public void getUsersByIdTest() {
        // Koristimo Mosesa sa ID-jem 1 koji provereno postoji u bazi
        Object userId = 1;
        
        findUserById(userId)
                .verifyId(userId)
                .verifyFirstname("Moses");
    }

    @Test
    public void getAllUsersStreamVerificationTest() {
        getAllUsers()
                .verifyUsernameById(1, "Moses")
                .verifyUsernameById(2, "Olatunde");
    }
}

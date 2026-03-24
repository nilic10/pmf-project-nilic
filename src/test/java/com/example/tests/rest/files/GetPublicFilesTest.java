package com.example.tests.rest.files;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("File Management")
public class GetPublicFilesTest extends RestClient {

    @Test
    @DisplayName("Get public files - Negative Test")
    public void getPublicFilesTest() {
        org.junit.jupiter.api.Assertions.assertThrows(org.springframework.web.client.HttpClientErrorException.NotFound.class, () -> {
            getPublicFiles();
        });
    }
}

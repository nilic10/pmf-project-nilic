package com.example.tests.rest;

import com.example.rest.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test suite for verifying that the login endpoint is not vulnerable to SQL injection.
 * Sends malicious payloads via REST API and verifies that the server rejects them with a 401 response.
 */
public class SqlInjectionLoginTest extends RestClient {

    /**
     * Tests that a SQL injection payload in the username field is rejected by the login endpoint.
     * Verifies that the server returns 401 Unauthorized instead of granting access.
     */
    @Test
    public void loginWithSqlInjectionPayloadShouldBeRejected() {
        assertThrows(HttpClientErrorException.Unauthorized.class, () ->
                login("admin' OR '1'='1", "some password"));
    }
}


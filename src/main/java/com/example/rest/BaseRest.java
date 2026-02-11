package com.example.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Base class for all REST client implementations.
 * Provides core functionality for making HTTP requests using RestTemplate.
 * 
 * @param <T> The type of data model managed by this client.
 */
public abstract class BaseRest<T> {

    protected RestTemplate restTemplate;
    protected HttpHeaders headers;
    protected T data;

    /**
     * Default base URL for the REST API.
     */
    public static final String DEFAULT_BASE_URL = "http://localhost:3000/api";

    /**
     * Retrieves the base URL from system properties or returns the default.
     * 
     * @return The base URL for the API.
     */
    public static String getBaseUrl() {
        return System.getProperty("baseUrl", DEFAULT_BASE_URL);
    }

    /**
     * Default constructor. Initializes RestTemplate and default headers.
     */
    public BaseRest() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    /**
     * Constructor that initializes the client with a specific data object.
     * 
     * @param data The initial data model instance.
     */
    public BaseRest(T data) {
        this();
        this.data = data;
    }

    /**
     * Gets the data model managed by this client.
     * 
     * @return The data model instance.
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data model managed by this client.
     * 
     * @param data The data model instance to set.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Sets the Bearer authentication token in the request headers.
     * 
     * @param token The JWT or other authentication token.
     */
    public void setToken(String token) {
        this.headers.setBearerAuth(token);
    }

    /**
     * Performs an HTTP GET request.
     * 
     * @param endpoint The API endpoint (relative to base URL).
     * @param responseType The expected response class type.
     * @param <R> The type of the response body.
     * @return A ResponseEntity containing the response.
     */
    public <R> ResponseEntity<R> get(String endpoint, Class<R> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.GET, entity, responseType);
    }

    /**
     * Performs an HTTP POST request.
     * 
     * @param endpoint The API endpoint (relative to base URL).
     * @param body The request body object.
     * @param responseType The expected response class type.
     * @param <R> The type of the response body.
     * @param <B> The type of the request body.
     * @return A ResponseEntity containing the response.
     */
    public <R, B> ResponseEntity<R> post(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.POST, entity, responseType);
    }

    /**
     * Performs an HTTP PUT request.
     * 
     * @param endpoint The API endpoint (relative to base URL).
     * @param body The request body object.
     * @param responseType The expected response class type.
     * @param <R> The type of the response body.
     * @param <B> The type of the request body.
     * @return A ResponseEntity containing the response.
     */
    public <R, B> ResponseEntity<R> put(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.PUT, entity, responseType);
    }

    /**
     * Performs an HTTP PATCH request.
     * 
     * @param endpoint The API endpoint (relative to base URL).
     * @param body The request body object.
     * @param responseType The expected response class type.
     * @param <R> The type of the response body.
     * @param <B> The type of the request body.
     * @return A ResponseEntity containing the response.
     */
    public <R, B> ResponseEntity<R> patch(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.PATCH, entity, responseType);
    }

    /**
     * Performs an HTTP DELETE request.
     * 
     * @param endpoint The API endpoint (relative to base URL).
     * @return A ResponseEntity containing the response.
     */
    public ResponseEntity<Void> delete(String endpoint) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.DELETE, entity, Void.class);
    }
}

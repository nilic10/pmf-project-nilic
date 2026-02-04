package com.example.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public abstract class BaseRest<T> {

    protected RestTemplate restTemplate;
    protected HttpHeaders headers;
    protected T data;

    public static final String DEFAULT_BASE_URL = "http://localhost:3000/api";

    public static String getBaseUrl() {
        return System.getProperty("baseUrl", DEFAULT_BASE_URL);
    }

    public BaseRest() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public BaseRest(T data) {
        this();
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setToken(String token) {
        this.headers.setBearerAuth(token);
    }

    public <R> ResponseEntity<R> get(String endpoint, Class<R> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.GET, entity, responseType);
    }

    public <R, B> ResponseEntity<R> post(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.POST, entity, responseType);
    }

    public <R, B> ResponseEntity<R> put(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.PUT, entity, responseType);
    }

    public <R, B> ResponseEntity<R> patch(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.PATCH, entity, responseType);
    }

    public ResponseEntity<Void> delete(String endpoint) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(getBaseUrl() + endpoint, HttpMethod.DELETE, entity, Void.class);
    }
}

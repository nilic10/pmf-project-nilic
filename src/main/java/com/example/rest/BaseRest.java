package com.example.rest;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public abstract class BaseRest<T> {

    protected RestTemplate restTemplate;
    protected String baseUrl;
    protected HttpHeaders headers;

    public BaseRest(String baseUrl) {
        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_JSON);
        this.headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    }

    public void setToken(String token) {
        this.headers.setBearerAuth(token);
    }

    public <R> ResponseEntity<R> get(String endpoint, Class<R> responseType) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.GET, entity, responseType);
    }

    public <R, B> ResponseEntity<R> post(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.POST, entity, responseType);
    }

    public <R, B> ResponseEntity<R> put(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.PUT, entity, responseType);
    }

    public <R, B> ResponseEntity<R> patch(String endpoint, B body, Class<R> responseType) {
        HttpEntity<B> entity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.PATCH, entity, responseType);
    }

    public ResponseEntity<Void> delete(String endpoint) {
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(baseUrl + endpoint, HttpMethod.DELETE, entity, Void.class);
    }
}

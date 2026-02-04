package com.example.rest.common.articles;

import com.example.rest.BaseRest;
import com.example.rest.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.ResponseEntity;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class ArticleClient extends Article {

    private static BaseRest<ArticleClient> rest;

    public static void init(String baseUrl) {
        rest = new BaseRest<ArticleClient>(baseUrl) {};
    }

    public static void setToken(String token) {
        rest.setToken(token);
    }

    public static ArticleClient findById(Object id) {
        return rest.get("/articles/" + id, ArticleClient.class).getBody();
    }

    public static ArticleClient[] getAll() {
        return rest.get("/articles", ArticleClient[].class).getBody();
    }

    public ArticleClient create() {
        return rest.post("/articles", this, ArticleClient.class).getBody();
    }

    public ArticleClient update() {
        return rest.put("/articles/" + this.getId(), this, ArticleClient.class).getBody();
    }

    public ArticleClient patch() {
        return rest.patch("/articles/" + this.getId(), this, ArticleClient.class).getBody();
    }

    public static void delete(Object id) {
        rest.delete("/articles/" + id);
    }

    public ArticleClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, getId(), "Article ID mismatch");
        return this;
    }

    public ArticleClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, getUser_id(), "User ID mismatch");
        return this;
    }

    public ArticleClient verifyTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, getTitle(), "Title mismatch");
        return this;
    }

    public ArticleClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, getBody(), "Body mismatch");
        return this;
    }

    public ArticleClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, getDate(), "Date mismatch");
        return this;
    }

    public ArticleClient verifyImage(String expectedImage) {
        Assertions.assertEquals(expectedImage, getImage(), "Image mismatch");
        return this;
    }
}

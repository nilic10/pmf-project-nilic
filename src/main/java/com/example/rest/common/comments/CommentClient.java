package com.example.rest.common.comments;

import com.example.rest.BaseRest;
import com.example.rest.models.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Assertions;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class CommentClient extends Comment {

    private static BaseRest<CommentClient> rest;

    public static void init(String baseUrl) {
        rest = new BaseRest<CommentClient>(baseUrl) {};
    }

    public static void setToken(String token) {
        rest.setToken(token);
    }

    public static CommentClient findById(Object id) {
        return rest.get("/comments/" + id, CommentClient.class).getBody();
    }

    public static CommentClient[] getAll() {
        return rest.get("/comments", CommentClient[].class).getBody();
    }

    public CommentClient create() {
        return rest.post("/comments", this, CommentClient.class).getBody();
    }

    public CommentClient update() {
        return rest.put("/comments/" + this.getId(), this, CommentClient.class).getBody();
    }

    public CommentClient patch() {
        return rest.patch("/comments/" + this.getId(), this, CommentClient.class).getBody();
    }

    public static void delete(Object id) {
        rest.delete("/comments/" + id);
    }

    public CommentClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, getId(), "Comment ID mismatch");
        return this;
    }

    public CommentClient verifyArticleId(Object expectedArticleId) {
        Assertions.assertEquals(expectedArticleId, getArticle_id(), "Article ID mismatch");
        return this;
    }

    public CommentClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, getUser_id(), "User ID mismatch");
        return this;
    }

    public CommentClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, getBody(), "Body mismatch");
        return this;
    }

    public CommentClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, getDate(), "Date mismatch");
        return this;
    }
}

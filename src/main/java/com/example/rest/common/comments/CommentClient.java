package com.example.rest.common.comments;

import com.example.rest.BaseRest;
import com.example.rest.models.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentClient extends BaseRest<Comment> {

    public CommentClient() {
        super();
    }

    public CommentClient findById(Object id) {
        Comment data = this.get("/comments/" + id, Comment.class).getBody();
        this.setData(data);
        return this;
    }

    public CommentClient create() {
        Comment responseData = this.post("/comments", this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    public CommentClient update() {
        Comment responseData = this.put("/comments/" + this.data.getId(), this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    public CommentClient patch() {
        Comment responseData = this.patch("/comments/" + this.data.getId(), this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    public void delete(Object id) {
        this.delete("/comments/" + id);
    }

    public CommentClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "Comment ID mismatch");
        return this;
    }

    public CommentClient verifyArticleId(Object expectedArticleId) {
        Assertions.assertEquals(expectedArticleId, data.getArticle_id(), "Article ID mismatch");
        return this;
    }

    public CommentClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, data.getUser_id(), "User ID mismatch");
        return this;
    }

    public CommentClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, data.getBody(), "Body mismatch");
        return this;
    }

    public CommentClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, data.getDate(), "Date mismatch");
        return this;
    }
}

package com.example.rest.common.comments;

import com.example.rest.BaseRest;
import com.example.rest.models.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

/**
 * Client for interacting with individual comment resources.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentClient extends BaseRest<Comment> {

    /**
     * Default constructor.
     */
    public CommentClient() {
        super();
    }

    /**
     * Retrieves a comment by its ID and populates this client's data.
     * 
     * @param id The ID of the comment to retrieve.
     * @return This CommentClient instance.
     */
    public CommentClient findById(Object id) {
        Comment data = this.get("/comments/" + id, Comment.class).getBody();
        this.setData(data);
        return this;
    }

    /**
     * Creates a new comment using the current data.
     * 
     * @return This CommentClient instance with created comment data.
     */
    public CommentClient create() {
        Comment responseData = this.post("/comments", this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Updates the comment using PUT (full update).
     * 
     * @return This CommentClient instance with updated data.
     */
    public CommentClient update() {
        Comment responseData = this.put("/comments/" + this.data.getId(), this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Updates the comment using PATCH (partial update).
     * 
     * @return This CommentClient instance with patched data.
     */
    public CommentClient patch() {
        Comment responseData = this.patch("/comments/" + this.data.getId(), this.data, Comment.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Deletes the comment with the specified ID.
     * 
     * @param id The ID of the comment to delete.
     */
    public void delete(Object id) {
        this.delete("/comments/" + id);
    }

    /**
     * Verifies that the comment ID matches the expected ID.
     * 
     * @param expectedId The expected comment ID.
     * @return This CommentClient instance.
     */
    public CommentClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "Comment ID mismatch");
        return this;
    }

    /**
     * Verifies that the comment's article ID matches the expected article ID.
     * 
     * @param expectedArticleId The expected article ID.
     * @return This CommentClient instance.
     */
    public CommentClient verifyArticleId(Object expectedArticleId) {
        Assertions.assertEquals(expectedArticleId, data.getArticle_id(), "Article ID mismatch");
        return this;
    }

    /**
     * Verifies that the comment's user ID matches the expected user ID.
     * 
     * @param expectedUserId The expected user ID.
     * @return This CommentClient instance.
     */
    public CommentClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, data.getUser_id(), "User ID mismatch");
        return this;
    }

    /**
     * Verifies that the comment body matches the expected body.
     * 
     * @param expectedBody The expected body content.
     * @return This CommentClient instance.
     */
    public CommentClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, data.getBody(), "Body mismatch");
        return this;
    }

    /**
     * Verifies that the comment date matches the expected date.
     * 
     * @param expectedDate The expected date string.
     * @return This CommentClient instance.
     */
    public CommentClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, data.getDate(), "Date mismatch");
        return this;
    }
}

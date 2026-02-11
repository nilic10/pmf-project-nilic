package com.example.rest.common.comments;

import com.example.rest.BaseRest;
import com.example.rest.models.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Client for interacting with the collection of all comments.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AllCommentsClient extends BaseRest<Comment> {

    private Comment[] comments;

    /**
     * Retrieves all comments from the API.
     * 
     * @return This AllCommentsClient instance with the retrieved comments.
     */
    public AllCommentsClient getAll() {
        comments = this.get("/comments", Comment[].class).getBody();
        return this;
    }

    /**
     * Verifies that the body of a comment with a specific ID matches the expected body.
     * 
     * @param id The ID of the comment to verify.
     * @param expectedBody The expected body content.
     * @return This AllCommentsClient instance.
     * @throws AssertionError if the comment is not found.
     */
    public AllCommentsClient verifyBodyById(Object id, String expectedBody) {
        Comment comment = Arrays.stream(this.comments)
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Comment with ID " + id + " not found"));

        Assertions.assertEquals(expectedBody, comment.getBody(), "Body mismatch for comment ID " + id);
        return this;
    }
}

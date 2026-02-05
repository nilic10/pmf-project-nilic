package com.example.rest.common.comments;

import com.example.rest.BaseRest;
import com.example.rest.models.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllCommentsClient extends BaseRest<Comment> {

    private Comment[] comments;

    public AllCommentsClient getAll() {
        comments = this.get("/comments", Comment[].class).getBody();
        return this;
    }

    public AllCommentsClient verifyBodyById(Object id, String expectedBody) {
        Comment comment = Arrays.stream(this.comments)
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Comment with ID " + id + " not found"));

        Assertions.assertEquals(expectedBody, comment.getBody(), "Body mismatch for comment ID " + id);
        return this;
    }
}

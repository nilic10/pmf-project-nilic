package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Data model representing a Comment.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    /** Unique identifier for the comment. */
    private Object id;
    /** ID of the article the comment belongs to. */
    private Object article_id;
    /** ID of the user who created the comment. */
    private Object user_id;
    /** Content of the comment. */
    private String body;
    /** Date when the comment was created. */
    private String date;
}

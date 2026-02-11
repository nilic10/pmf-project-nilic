package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Data model representing an Article.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    /** Unique identifier for the article. */
    private Object id;
    /** ID of the user who created the article. */
    private Object user_id;
    /** Title of the article. */
    private String title;
    /** Main content of the article. */
    private String body;
    /** Date when the article was created or published. */
    private String date;
    /** URL or path to the article's image. */
    private String image;
}

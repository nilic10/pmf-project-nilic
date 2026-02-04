package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Comment {
    private Object id;
    private Object article_id;
    private Object user_id;
    private String body;
    private String date;
}

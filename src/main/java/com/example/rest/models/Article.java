package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Article {
    private Object id;
    private Object user_id;
    private String title;
    private String body;
    private String date;
    private String image;
}

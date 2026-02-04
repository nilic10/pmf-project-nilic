package com.example.rest.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginToken {
    private String access_token;
}

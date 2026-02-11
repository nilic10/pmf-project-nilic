package com.example.rest.models;

import lombok.*;

/**
 * Data model for the authentication response containing the access token.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginToken {
    /** The Bearer access token. */
    private String access_token;
}

package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.Comment;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Epic("REST API Tests")
@Feature("Comment Management")
/**
 * Test suite for XSS injection detection in comment creation via REST API.
 * Verifies that comment bodies containing script tags are sanitized before being stored,
 * preventing cross-site scripting (XSS) vulnerabilities.
 */
public class XssCreateCommentInjectionTest extends RestClient {

    private static final String DEFAULT_PASSWORD = "password1!";
    private static final String DEFAULT_AVATAR = "https://example.com/avatar.jpg";
    private static final String COMMENT_BODY = "This is an automated comment.";
    private static final String COMMENT_DATE = "2026-05-20T10:00:00Z";
    private static final Object ARTICLE_ID = 1;

    private UserClient testUser;
    private String token;

    /**
     * Creates a unique test user and retrieves an authentication token before each test.
     * Ensures test isolation by using a randomly generated email address per run.
     */
    @BeforeEach
    public void setup() {
        String uniqueEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        User newUser = User.builder()
                .email(uniqueEmail)
                .firstname("Test")
                .lastname("User")
                .password(DEFAULT_PASSWORD)
                .avatar(DEFAULT_AVATAR)
                .build();

        testUser = createUser(newUser);

        token = login(uniqueEmail, DEFAULT_PASSWORD);
    }

    /**
     * Tests that a comment body containing an XSS script tag payload is sanitized by the API.
     * Verifies that the stored comment body does not contain raw {@code <script>} tags,
     * indicating the application is protected against cross-site scripting attacks.
     */


    @Test
    @DisplayName("Create new comment - xss payload in comment")
    public void xssPayloadInCommentShouldBeSanitized() {
        Comment newComment = Comment.builder()
                .article_id(ARTICLE_ID)
                .user_id(testUser.getData().getId())
                .body("<script>alert('XSS')</script>")
                .date(COMMENT_DATE)
                .build();

        String responseBody = createComment(token, newComment).getData().getBody();
        assertFalse(responseBody.contains("<script>"),
                "Comment body contains an unsanitized <script> tag – possible XSS vulnerability");

    }
}

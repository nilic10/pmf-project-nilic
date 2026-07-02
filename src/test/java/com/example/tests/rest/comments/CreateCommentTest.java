package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import com.example.rest.common.comments.CommentClient;
import com.example.rest.models.Comment;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Epic("REST API Tests")
@Feature("Comment Management")
/**
 * Test suite for creating comments via REST API.
 * Verifies that a new comment can be successfully created by an authenticated user on an existing article.
 */
public class CreateCommentTest extends RestClient {

    private static final String DEFAULT_PASSWORD = "password1!";
    private static final String DEFAULT_AVATAR = "https://example.com/avatar.jpg";
    private static final String COMMENT_BODY = "This is an automated comment.";
    private static final String COMMENT_DATE = "2024-05-20T10:00:00Z";
    private static final Object ARTICLE_ID = 1;

    private UserClient testUser;
    private String token;

    /**
     * Creates a unique test user and retrieves an authentication token before each test.
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
     * Tests that a new comment can be created with valid data by an authenticated user.
     * Verifies the body, article ID, and user ID of the created comment.
     */
    @Test
    @DisplayName("Create new comment - Positive Test")
    public void createCommentTest() {
        Comment newComment = Comment.builder()
                .article_id(ARTICLE_ID)
                .user_id(testUser.getData().getId())
                .body(COMMENT_BODY)
                .date(COMMENT_DATE)
                .build();

        createComment(token, newComment)
                .verifyBody(COMMENT_BODY)
                .verifyArticleId(ARTICLE_ID)
                .verifyUserId(testUser.getData().getId());
    }
}

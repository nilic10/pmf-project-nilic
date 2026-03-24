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
public class CreateCommentTest extends RestClient {

    private static final String DEFAULT_PASSWORD = "password1!";
    private static final String DEFAULT_AVATAR = "https://example.com/avatar.jpg";
    private static final String COMMENT_BODY = "This is an automated comment.";
    private static final String COMMENT_DATE = "2024-05-20T10:00:00Z";
    private static final Object ARTICLE_ID = 1;

    private User testUser;
    private String token;

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

        UserClient userClient = new UserClient();
        userClient.setData(newUser);
        testUser = userClient.create().getData();

        token = login(uniqueEmail, DEFAULT_PASSWORD);
    }

    @Test
    @DisplayName("Create new comment - Positive Test")
    public void createCommentTest() {
        Comment newComment = Comment.builder()
                .article_id(ARTICLE_ID)
                .user_id(testUser.getId())
                .body(COMMENT_BODY)
                .date(COMMENT_DATE)
                .build();

        CommentClient commentClient = new CommentClient();
        commentClient.setToken(token);
        commentClient.setData(newComment);

        createComment(commentClient)
                .verifyBody(COMMENT_BODY)
                .verifyArticleId(ARTICLE_ID)
                .verifyUserId(testUser.getId());
    }
}

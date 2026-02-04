package com.example.rest;

import com.example.rest.common.articles.ArticleClient;
import com.example.rest.common.comments.CommentClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.UserLoginData;
import com.example.rest.models.UserLoginToken;
import org.springframework.http.ResponseEntity;

public class RestClient extends BaseRest<Object> {

    public RestClient(String baseUrl) {
        super(baseUrl);
    }

    public void login(String email, String password) {
        UserLoginData loginData = UserLoginData.builder()
                .email(email)
                .password(password)
                .build();
        ResponseEntity<UserLoginToken> response = post("/login", loginData, UserLoginToken.class);
        if (response.getBody() != null) {
            setToken(response.getBody().getAccess_token());
        }
    }

    public ArticleClient findArticleById(Object id) {
        ResponseEntity<ArticleClient> response = get("/articles/" + id, ArticleClient.class);
        return response.getBody();
    }

    public ArticleClient[] getAllArticles() {
        ResponseEntity<ArticleClient[]> response = get("/articles", ArticleClient[].class);
        return response.getBody();
    }

    public ArticleClient createArticle(ArticleClient article) {
        ResponseEntity<ArticleClient> response = post("/articles", article, ArticleClient.class);
        return response.getBody();
    }

    public ArticleClient updateArticle(Object id, ArticleClient article) {
        ResponseEntity<ArticleClient> response = put("/articles/" + id, article, ArticleClient.class);
        return response.getBody();
    }

    public ArticleClient patchArticle(Object id, ArticleClient article) {
        ResponseEntity<ArticleClient> response = patch("/articles/" + id, article, ArticleClient.class);
        return response.getBody();
    }

    public void deleteArticle(Object id) {
        delete("/articles/" + id);
    }

    public CommentClient findCommentById(Object id) {
        ResponseEntity<CommentClient> response = get("/comments/" + id, CommentClient.class);
        return response.getBody();
    }

    public CommentClient[] getAllComments() {
        ResponseEntity<CommentClient[]> response = get("/comments", CommentClient[].class);
        return response.getBody();
    }

    public CommentClient createComment(CommentClient comment) {
        ResponseEntity<CommentClient> response = post("/comments", comment, CommentClient.class);
        return response.getBody();
    }

    public CommentClient updateComment(Object id, CommentClient comment) {
        ResponseEntity<CommentClient> response = put("/comments/" + id, comment, CommentClient.class);
        return response.getBody();
    }

    public CommentClient patchComment(Object id, CommentClient comment) {
        ResponseEntity<CommentClient> response = patch("/comments/" + id, comment, CommentClient.class);
        return response.getBody();
    }

    public void deleteComment(Object id) {
        delete("/comments/" + id);
    }

    public UserClient findUserById(Object id) {
        ResponseEntity<UserClient> response = get("/users/" + id, UserClient.class);
        return response.getBody();
    }

    public UserClient[] getAllUsers() {
        ResponseEntity<UserClient[]> response = get("/users", UserClient[].class);
        return response.getBody();
    }

    public UserClient createUser(UserClient user) {
        ResponseEntity<UserClient> response = post("/users", user, UserClient.class);
        return response.getBody();
    }

    public UserClient updateUser(Object id, UserClient user) {
        ResponseEntity<UserClient> response = put("/users/" + id, user, UserClient.class);
        return response.getBody();
    }

    public UserClient patchUser(Object id, UserClient user) {
        ResponseEntity<UserClient> response = patch("/users/" + id, user, UserClient.class);
        return response.getBody();
    }

    public void deleteUser(Object id) {
        delete("/users/" + id);
    }

    // Dodatne metode po potrebi...
}

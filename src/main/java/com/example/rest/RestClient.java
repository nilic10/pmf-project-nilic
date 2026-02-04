package com.example.rest;

import com.example.rest.common.articles.ArticleClient;
import com.example.rest.common.comments.CommentClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.UserLoginData;
import com.example.rest.models.UserLoginToken;
import org.springframework.http.ResponseEntity;

public class RestClient extends BaseRest<Object> {

    public RestClient() {
        super();
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
        return new ArticleClient().findById(id);
    }

    public ArticleClient[] getAllArticles() {
        return new ArticleClient().getAll();
    }

    public ArticleClient createArticle(ArticleClient article) {
        return article.create();
    }

    public ArticleClient updateArticle(Object id, ArticleClient article) {
        // Postavljamo ID ako nije postavljen da bi update() radio ispravno
        if (article.getData() != null) {
            article.getData().setId(id);
        }
        return article.update();
    }

    public ArticleClient patchArticle(Object id, ArticleClient article) {
        if (article.getData() != null) {
            article.getData().setId(id);
        }
        return article.patch();
    }

    public void deleteArticle(Object id) {
        new ArticleClient().delete(id);
    }

    public CommentClient findCommentById(Object id) {
        return new CommentClient().findById(id);
    }

    public CommentClient[] getAllComments() {
        return new CommentClient().getAll();
    }

    public CommentClient createComment(CommentClient comment) {
        return comment.create();
    }

    public CommentClient updateComment(Object id, CommentClient comment) {
        if (comment.getData() != null) {
            comment.getData().setId(id);
        }
        return comment.update();
    }

    public CommentClient patchComment(Object id, CommentClient comment) {
        if (comment.getData() != null) {
            comment.getData().setId(id);
        }
        return comment.patch();
    }

    public void deleteComment(Object id) {
        new CommentClient().delete(id);
    }

    public UserClient findUserById(Object id) {
        return new UserClient().findById(id);
    }

    public UserClient[] getAllUsers() {
        return new UserClient().getAll();
    }

    public UserClient createUser(UserClient user) {
        return user.create();
    }

    public UserClient updateUser(Object id, UserClient user) {
        if (user.getData() != null) {
            user.getData().setId(id);
        }
        return user.update();
    }

    public UserClient patchUser(Object id, UserClient user) {
        if (user.getData() != null) {
            user.getData().setId(id);
        }
        return user.patch();
    }

    public void deleteUser(Object id) {
        new UserClient().delete(id);
    }

    // Dodatne metode po potrebi...
}

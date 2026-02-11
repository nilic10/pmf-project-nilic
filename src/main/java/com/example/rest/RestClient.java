package com.example.rest;

import com.example.rest.common.articles.AllArticlesClient;
import com.example.rest.common.articles.ArticleClient;
import com.example.rest.common.comments.AllCommentsClient;
import com.example.rest.common.comments.CommentClient;
import com.example.rest.common.files.AllFilesClient;
import com.example.rest.common.users.AllUsersClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.UserLoginData;
import com.example.rest.models.UserLoginToken;
import org.springframework.http.ResponseEntity;

/**
 * Main client for interacting with the REST API.
 * Provides a high-level API for managing articles, comments, users, and files.
 */
public class RestClient extends BaseRest<Object> {

    /**
     * Default constructor.
     */
    public RestClient() {
        super();
    }

    /**
     * Authenticates a user and sets the access token for subsequent requests.
     * 
     * @param email User's email.
     * @param password User's password.
     */
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

    /**
     * Finds an article by its ID.
     * 
     * @param id The ID of the article to find.
     * @return An ArticleClient instance populated with the article data.
     */
    public ArticleClient findArticleById(Object id) {
        return new ArticleClient().findById(id);
    }

    /**
     * Retrieves all articles.
     * 
     * @return An AllArticlesClient instance containing all articles.
     */
    public AllArticlesClient getAllArticles() {
        return new AllArticlesClient().getAll();
    }

    /**
     * Creates a new article.
     * 
     * @param article The ArticleClient instance containing the article data to create.
     * @return The created ArticleClient instance.
     */
    public ArticleClient createArticle(ArticleClient article) {
        return article.create();
    }

    /**
     * Updates an existing article using PUT (full update).
     * 
     * @param id The ID of the article to update.
     * @param article The ArticleClient instance containing the new data.
     * @return The updated ArticleClient instance.
     */
    public ArticleClient updateArticle(Object id, ArticleClient article) {
        // Postavljamo ID ako nije postavljen da bi update() radio ispravno
        if (article.getData() != null) {
            article.getData().setId(id);
        }
        return article.update();
    }

    /**
     * Updates an existing article using PATCH (partial update).
     * 
     * @param id The ID of the article to patch.
     * @param article The ArticleClient instance containing the partial data.
     * @return The patched ArticleClient instance.
     */
    public ArticleClient patchArticle(Object id, ArticleClient article) {
        if (article.getData() != null) {
            article.getData().setId(id);
        }
        return article.patch();
    }

    /**
     * Deletes an article by its ID.
     * 
     * @param id The ID of the article to delete.
     */
    public void deleteArticle(Object id) {
        new ArticleClient().delete(id);
    }

    /**
     * Finds a comment by its ID.
     * 
     * @param id The ID of the comment to find.
     * @return A CommentClient instance populated with the comment data.
     */
    public CommentClient findCommentById(Object id) {
        return new CommentClient().findById(id);
    }

    /**
     * Retrieves all comments.
     * 
     * @return An AllCommentsClient instance containing all comments.
     */
    public AllCommentsClient getAllComments() {
        return new AllCommentsClient().getAll();
    }

    /**
     * Creates a new comment.
     * 
     * @param comment The CommentClient instance containing the comment data to create.
     * @return The created CommentClient instance.
     */
    public CommentClient createComment(CommentClient comment) {
        return comment.create();
    }

    /**
     * Updates an existing comment using PUT (full update).
     * 
     * @param id The ID of the comment to update.
     * @param comment The CommentClient instance containing the new data.
     * @return The updated CommentClient instance.
     */
    public CommentClient updateComment(Object id, CommentClient comment) {
        if (comment.getData() != null) {
            comment.getData().setId(id);
        }
        return comment.update();
    }

    /**
     * Updates an existing comment using PATCH (partial update).
     * 
     * @param id The ID of the comment to patch.
     * @param comment The CommentClient instance containing the partial data.
     * @return The patched CommentClient instance.
     */
    public CommentClient patchComment(Object id, CommentClient comment) {
        if (comment.getData() != null) {
            comment.getData().setId(id);
        }
        return comment.patch();
    }

    /**
     * Deletes a comment by its ID.
     * 
     * @param id The ID of the comment to delete.
     */
    public void deleteComment(Object id) {
        new CommentClient().delete(id);
    }

    /**
     * Finds a user by their ID.
     * 
     * @param id The ID of the user to find.
     * @return A UserClient instance populated with the user data.
     */
    public UserClient findUserById(Object id) {
        return new UserClient().findById(id);
    }

    /**
     * Retrieves all users.
     * 
     * @return An AllUsersClient instance containing all users.
     */
    public AllUsersClient getAllUsers() {
        return new AllUsersClient().getAll();
    }

    /**
     * Creates a new user.
     * 
     * @param user The UserClient instance containing the user data to create.
     * @return The created UserClient instance.
     */
    public UserClient createUser(UserClient user) {
        return user.create();
    }

    /**
     * Updates an existing user using PUT (full update).
     * 
     * @param id The ID of the user to update.
     * @param user The UserClient instance containing the new data.
     * @return The updated UserClient instance.
     */
    public UserClient updateUser(Object id, UserClient user) {
        if (user.getData() != null) {
            user.getData().setId(id);
        }
        return user.update();
    }

    /**
     * Updates an existing user using PATCH (partial update).
     * 
     * @param id The ID of the user to patch.
     * @param user The UserClient instance containing the partial data.
     * @return The patched UserClient instance.
     */
    public UserClient patchUser(Object id, UserClient user) {
        if (user.getData() != null) {
            user.getData().setId(id);
        }
        return user.patch();
    }

    /**
     * Deletes a user by their ID.
     * 
     * @param id The ID of the user to delete.
     */
    public void deleteUser(Object id) {
        new UserClient().delete(id);
    }

    /**
     * Retrieves all files uploaded by the authenticated user.
     * 
     * @return An AllFilesClient instance containing the uploaded files.
     */
    public AllFilesClient getUploadedFiles() {
        return new AllFilesClient().getUploadedFiles();
    }

    /**
     * Retrieves all public files.
     * 
     * @return An AllFilesClient instance containing the public files.
     */
    public AllFilesClient getPublicFiles() {
        return new AllFilesClient().getPublicFiles();
    }
}

package com.example.rest;

import com.example.rest.common.articles.AllArticlesClient;
import com.example.rest.common.articles.ArticleClient;
import com.example.rest.common.comments.AllCommentsClient;
import com.example.rest.common.comments.CommentClient;
import com.example.rest.common.files.AllFilesClient;
import com.example.rest.common.files.FileClient;
import com.example.rest.common.users.AllUsersClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.Article;
import com.example.rest.models.Comment;
import com.example.rest.models.File;
import com.example.rest.models.User;
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
     * Authenticates a user and returns the access token.
     * 
     * @param email User's email.
     * @param password User's password.
     * @return The access token as a String.
     */
    public String login(String email, String password) {
        UserLoginData loginData = UserLoginData.builder()
                .email(email)
                .password(password)
                .build();
        ResponseEntity<UserLoginToken> response = post("/login", loginData, UserLoginToken.class);
        if (response.getBody() != null) {
            return response.getBody().getAccess_token();
        }
        return null;
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
     * @param token The bearer token for authorization.
     * @param article The Article model containing the article data to create.
     * @return The created ArticleClient instance.
     */
    public ArticleClient createArticle(String token, Article article) {
        return new ArticleClient().create(token, article);
    }

    /**
     * Updates an existing article using PUT (full update).
     * 
     * @param token The bearer token for authorization.
     * @param id The ID of the article to update.
     * @param article The Article model containing the new data.
     * @return The updated ArticleClient instance.
     */
    public ArticleClient updateArticle(String token, Object id, Article article) {
        if (article != null) {
            article.setId(id);
        }
        return new ArticleClient().update(token, article);
    }

    /**
     * Updates an existing article using PATCH (partial update).
     * 
     * @param token The bearer token for authorization.
     * @param id The ID of the article to patch.
     * @param article The Article model containing the partial data.
     * @return The patched ArticleClient instance.
     */
    public ArticleClient patchArticle(String token, Object id, Article article) {
        if (article != null) {
            article.setId(id);
        }
        return new ArticleClient().patch(token, article);
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
     * @param token The bearer token for authorization.
     * @param comment The Comment model containing the comment data to create.
     * @return The created CommentClient instance.
     */
    public CommentClient createComment(String token, Comment comment) {
        return new CommentClient().create(token, comment);
    }

    /**
     * Updates an existing comment using PUT (full update).
     * 
     * @param token The bearer token for authorization.
     * @param id The ID of the comment to update.
     * @param comment The Comment model containing the new data.
     * @return The updated CommentClient instance.
     */
    public CommentClient updateComment(String token, Object id, Comment comment) {
        if (comment != null) {
            comment.setId(id);
        }
        return new CommentClient().update(token, comment);
    }

    /**
     * Updates an existing comment using PATCH (partial update).
     * 
     * @param token The bearer token for authorization.
     * @param id The ID of the comment to patch.
     * @param comment The Comment model containing the partial data.
     * @return The patched CommentClient instance.
     */
    public CommentClient patchComment(String token, Object id, Comment comment) {
        if (comment != null) {
            comment.setId(id);
        }
        return new CommentClient().patch(token, comment);
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
     * @param user The User model containing the user data to create.
     * @return The created UserClient instance.
     */
    public UserClient createUser( User user) {
        UserClient userClient = new UserClient();
        return userClient.create(user);
    }

    /**
     * Updates an existing user using PUT (full update).
     * 
     * @param id The ID of the user to update.
     * @param user The User model containing the new data.
     * @return The updated UserClient instance.
     */
    public UserClient updateUser(Object id, User user) {
        if (user != null) {
            user.setId(id);
        }
        UserClient userClient = new UserClient();
        return userClient.update(user);
    }

    /**
     * Updates an existing user using PATCH (partial update).
     * 
     * @param id The ID of the user to patch.
     * @param user The User model containing the partial data.
     * @return The patched UserClient instance.
     */
    public UserClient patchUser(Object id, User user) {
        if (user != null) {
            user.setId(id);
        }
        UserClient userClient = new UserClient();
        return userClient.patch(user);
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
     * Creates a new file entry.
     *
     * @param token The bearer token for authorization.
     * @param file The File model containing the file data to create.
     * @return The created FileClient instance.
     */
    public FileClient createFile(String token, File file) {
        return new FileClient().create(token, file);
    }

    /**
     * Updates file information using PUT (full update).
     *
     * @param token The bearer token for authorization.
     * @param file The File model containing the new data.
     * @return The updated FileClient instance.
     */
    public FileClient updateFile(String token, File file) {
        return new FileClient().update(token, file);
    }

    /**
     * Updates file information using PATCH (partial update).
     *
     * @param token The bearer token for authorization.
     * @param file The File model containing the partial data.
     * @return The patched FileClient instance.
     */
    public FileClient patchFile(String token, File file) {
        return new FileClient().patch(token, file);
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

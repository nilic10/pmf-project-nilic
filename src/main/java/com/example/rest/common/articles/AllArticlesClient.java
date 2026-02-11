package com.example.rest.common.articles;

import com.example.rest.BaseRest;
import com.example.rest.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Client for interacting with the collection of all articles.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AllArticlesClient extends BaseRest<Article> {

    private Article[] articles;

    /**
     * Retrieves all articles from the API.
     * 
     * @return This AllArticlesClient instance with the retrieved articles.
     */
    public AllArticlesClient getAll() {
        articles = this.get("/articles", Article[].class).getBody();
        return this;
    }

    /**
     * Verifies that the title of an article with a specific ID matches the expected title.
     * 
     * @param id The ID of the article to verify.
     * @param expectedTitle The expected title.
     * @return This AllArticlesClient instance.
     * @throws AssertionError if the article is not found.
     */
    public AllArticlesClient verifyTitleById(Object id, String expectedTitle) {
        Article article = Arrays.stream(this.articles)
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Article with ID " + id + " not found"));

        Assertions.assertEquals(expectedTitle, article.getTitle(), "Title mismatch for article ID " + id);
        return this;
    }
}

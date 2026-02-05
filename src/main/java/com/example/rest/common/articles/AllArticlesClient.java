package com.example.rest.common.articles;

import com.example.rest.BaseRest;
import com.example.rest.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllArticlesClient extends BaseRest<Article> {

    private Article[] articles;

    public AllArticlesClient getAll() {
        articles = this.get("/articles", Article[].class).getBody();
        return this;
    }

    public AllArticlesClient verifyTitleById(Object id, String expectedTitle) {
        Article article = Arrays.stream(this.articles)
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Article with ID " + id + " not found"));

        Assertions.assertEquals(expectedTitle, article.getTitle(), "Title mismatch for article ID " + id);
        return this;
    }
}

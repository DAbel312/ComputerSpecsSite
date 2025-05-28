package de.computerspecs.backend.service;

import de.computerspecs.backend.entity.Article;
import de.computerspecs.backend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * class to save Article in db
 */

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    /**
     * saves article in db and maps ArticleDto to Article
     * @param title
     * @param content
     * @param date
     * @param author
     * @return
     */

    public ResponseEntity<?> createArticle(String title, String content, Date date, String author) {
        try {
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);
            article.setAuthor(author);
            article.setDate(date);
            articleRepository.save(article);
            return ResponseEntity.status(HttpStatus.CREATED).body("Article was created successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Article could not be created!");
        }
    }
}

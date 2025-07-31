package de.computerspecs.backend.service;

import de.computerspecs.backend.entity.Article;
import de.computerspecs.backend.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

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

    /**
     * gets 2 newest articles from db
     * when IntelliJ says it Pageable and PageRequest need to cast, the imports are wrong
     * @return
     */

    public List<Article> getArticle() {
        Pageable pageable = PageRequest.of(0, 2);
        return articleRepository.getArticleDesc(pageable);
    }

    /**
     * gets all articles from db
     * @return
     */

    public List<Article> getAllArticles() {
        return articleRepository.getAllArticles();
    }
}

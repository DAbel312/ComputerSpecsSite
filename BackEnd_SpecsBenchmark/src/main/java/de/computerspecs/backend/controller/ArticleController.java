package de.computerspecs.backend.controller;

import de.computerspecs.backend.dto.ArticleDTO;
import de.computerspecs.backend.entity.Article;
import de.computerspecs.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Controller for managing articles in the news area
 */

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * endpoint for creating new article
     * @param articleDto
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity createArticle(@RequestBody ArticleDTO articleDto) {
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);

        return articleService.createArticle(articleDto.getTitle(), articleDto.getContent(), date, articleDto.getAuthor());
    }

    /**
     * endpoint to get all articles from database
     * @return
     */

    @GetMapping("/get")
    public List<Article> getArticles() {
        return articleService.getArticle();
    }

    /**
     * endpoint to get all articles from the db
     * @return
     */

    @GetMapping("/getAll")
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }
 }

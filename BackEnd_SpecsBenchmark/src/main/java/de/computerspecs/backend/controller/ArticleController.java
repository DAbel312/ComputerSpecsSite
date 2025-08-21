package de.computerspecs.backend.controller;

import de.computerspecs.backend.dto.ArticleCreateDTO;
import de.computerspecs.backend.dto.ArticleDTO;
import de.computerspecs.backend.entity.Article;
import de.computerspecs.backend.service.ArticleMapperService;
import de.computerspecs.backend.service.ArticleService;
import jakarta.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    ArticleMapperService articleMapperService;

    /**
     * endpoint for creating new article
     * @param articleDto
     * @return
     */

    @PostMapping("/create")
    public ResponseEntity createArticle(@RequestBody ArticleCreateDTO articleDto) {
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);

        return articleService.createArticle(articleDto.title(), articleDto.content(), date, articleDto.author(), articleDto.imageId());
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

    /**
     * endpoint to get one article from db by article id
     * @param id
     * @return
     */

    @GetMapping("/getById/{id}")
    public ArticleDTO getArticleById(@PathVariable("id") int id) {
        return articleMapperService.mapArticleToArticleDTO(articleService.getArticleById(id));
    }  
    
    /**
     * endpoint to get three random articles
     * @return
     */

    @GetMapping("/getRandom")
    public List<ArticleDTO> getRandomArticle() {
        return articleService.getRandomArticle()
                            .stream()
                            .map(articleMapperService::mapArticleToArticleDTO)
                            .toList();
    }
 }

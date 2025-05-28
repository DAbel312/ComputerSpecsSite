package de.computerspecs.backend.controller;

import de.computerspecs.backend.dto.ArticleDTO;
import de.computerspecs.backend.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
}

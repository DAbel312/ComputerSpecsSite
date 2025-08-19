package de.computerspecs.backend.service;

import org.springframework.stereotype.Component;

import de.computerspecs.backend.dto.ArticleDTO;
import de.computerspecs.backend.entity.Article;;

@Component
public class ArticleMapperService {
    
    public ArticleDTO mapArticleToArticleDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setDate(article.getDate());
        articleDTO.setAuthor(article.getAuthor());
        return articleDTO;
    }

}

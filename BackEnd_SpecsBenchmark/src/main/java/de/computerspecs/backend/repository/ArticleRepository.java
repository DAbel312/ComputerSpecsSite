package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a ORDER BY a.id DESC")
    List<Article> getArticleDesc(Pageable pageable);

    @Query("SELECT a FROM Article a ORDER BY a.id DESC")
    List<Article> getAllArticles();
}

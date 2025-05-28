package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.Article;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ArticleRepository extends JpaRepository<Article, Long> {


    @Transactional
    @Modifying
    @Query(value = "INSERT INTO article (title, content, date, author) VALUES (:title, :content, :date, :author)", nativeQuery = true)
    void saveArticle(String content, String title, Date date, String author);
}

package de.computerspecs.backend.repository;

import de.computerspecs.backend.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import de.computerspecs.backend.dto.ArticleDTO;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT new de.computerspecs.backend.dto.ArticleDTO(a.id, a.title, a.content, a.date, a.author) FROM Article a ORDER BY a.id DESC")
    List<ArticleDTO> getArticleDesc(Pageable pageable);

    @Query("SELECT a FROM Article a ORDER BY a.id DESC")
    List<Article> getAllArticles();

    @Query("SELECT a FROM Article a ORDER BY function('random')")
    List<Article> getRandomArticles(Pageable pageable);

    @Query("""
    SELECT new de.computerspecs.backend.dto.ArticleDTO(
        a.id, a.title, a.content, a.date, a.author
    )
    FROM Article a
    WHERE a.id = :id
    """)
    ArticleDTO getArticleByArticleId(@Param("id") long id);

}

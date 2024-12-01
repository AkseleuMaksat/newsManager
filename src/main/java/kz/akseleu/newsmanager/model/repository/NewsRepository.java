package kz.akseleu.newsmanager.model.repository;

import kz.akseleu.newsmanager.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n WHERE " +
            "(?1 IS NULL OR n.title LIKE %?1%) AND " +
            "(?2 IS NULL OR n.content LIKE %?2%) AND " +
            "(?3 IS NULL OR n.author.id = ?3)")
    List<News> searchNews(Optional<String> title, Optional<String> content, Optional<Long> authorId);
}


package kz.akseleu.newsmanager.repository;

import kz.akseleu.newsmanager.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n LEFT JOIN FETCH n.tags")
    List<News> findAllWithTags();
}


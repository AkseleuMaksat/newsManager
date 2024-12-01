package kz.akseleu.newsmanager.model.repository;

import kz.akseleu.newsmanager.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}

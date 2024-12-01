package kz.akseleu.newsmanager.model.repository;

import kz.akseleu.newsmanager.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}

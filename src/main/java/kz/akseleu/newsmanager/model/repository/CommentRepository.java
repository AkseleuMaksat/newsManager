package kz.akseleu.newsmanager.model.repository;

import kz.akseleu.newsmanager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

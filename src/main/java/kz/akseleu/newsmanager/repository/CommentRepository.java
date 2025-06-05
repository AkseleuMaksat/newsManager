package kz.akseleu.newsmanager.repository;

import kz.akseleu.newsmanager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

package kz.akseleu.newsmanager.service;
import org.springframework.transaction.annotation.Transactional;

import kz.akseleu.newsmanager.dto.CommentDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.mapper.CommentMapper;
import kz.akseleu.newsmanager.model.Comment;
import kz.akseleu.newsmanager.model.News;
import kz.akseleu.newsmanager.repository.CommentRepository;
import kz.akseleu.newsmanager.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository,
                          CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.commentMapper = commentMapper;
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + id));
    }

    @Transactional
    public Comment createComment(Comment comment) {
        if (comment.getNews() != null) {
            News news = newsRepository.findById(comment.getNews().getId())
                    .orElseThrow(() -> new IllegalArgumentException("News not found with id: " + comment.getNews().getId()));
            comment.setNews(news);
        }
        return commentRepository.save(comment);
    }

    @Transactional
    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new IllegalArgumentException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}

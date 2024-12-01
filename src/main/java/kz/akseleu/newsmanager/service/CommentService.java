package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.model.Comment;
import kz.akseleu.newsmanager.model.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment commentDetails) {
        Comment comment = getCommentById(id);

        comment.setContent(commentDetails.getContent());
        comment.setCreated(commentDetails.getCreated());
        comment.setModified(commentDetails.getModified());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }
}

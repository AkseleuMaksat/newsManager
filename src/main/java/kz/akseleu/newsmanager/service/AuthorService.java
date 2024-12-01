package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.dto.AuthorDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.model.Author;
import kz.akseleu.newsmanager.model.News;
import kz.akseleu.newsmanager.model.repository.AuthorRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(author -> new AuthorDTO(
                        author.getId(),
                        author.getName(),
                        author.getNews().stream().map(news -> news.getTitle()).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getNews().stream().map(news -> news.getTitle()).collect(Collectors.toList())
        );
    }

    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author = authorRepository.save(author);
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getNews().stream().map(news -> news.getTitle()).collect(Collectors.toList())
        );
    }

    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        author.setName(authorDTO.getName());
        author = authorRepository.save(author);
        return new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getNews().stream().map(news -> news.getTitle()).collect(Collectors.toList())
        );
    }

    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.delete(author);
    }
}

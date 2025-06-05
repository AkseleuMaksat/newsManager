package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.dto.AuthorDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.mapper.AuthorMapper;
import kz.akseleu.newsmanager.model.Author;
import kz.akseleu.newsmanager.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElse(null);
    }

    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }


    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = authorRepository.findById(id).orElse(null);
        author.setName(updatedAuthor.getName());
        return authorRepository.save(author);
    }

    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            System.out.println("not exist");
        }
        authorRepository.deleteById(id);
    }
}


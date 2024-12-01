package kz.akseleu.newsmanager.controller;

import kz.akseleu.newsmanager.dto.AuthorDTO;
import kz.akseleu.newsmanager.model.Author;
import kz.akseleu.newsmanager.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(authorDTO);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO newAuthorDTO = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(newAuthorDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        AuthorDTO updatedAuthorDTO = authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.ok(updatedAuthorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

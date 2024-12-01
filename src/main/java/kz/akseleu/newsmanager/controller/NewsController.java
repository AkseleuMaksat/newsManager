package kz.akseleu.newsmanager.controller;

import kz.akseleu.newsmanager.dto.NewsDTO;
import kz.akseleu.newsmanager.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kz.akseleu.newsmanager.service.NewsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsService newsService;
    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsDTO> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        NewsDTO newsDTO = newsService.getNewsById(id);
        return ResponseEntity.ok(newsDTO);
    }

    @PostMapping
    public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO) {
        NewsDTO newNewsDTO = newsService.createNews(newsDTO);
        return ResponseEntity.ok(newNewsDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNews(@PathVariable Long id, @RequestBody NewsDTO newsDTO) {
        NewsDTO updatedNewsDTO = newsService.updateNews(id, newsDTO);
        return ResponseEntity.ok(updatedNewsDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ResponseEntity.noContent().build();
    }
}

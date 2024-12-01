package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.dto.NewsDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.model.Comment;
import kz.akseleu.newsmanager.model.News;
import kz.akseleu.newsmanager.model.repository.NewsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    @Autowired
    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    public List<NewsDTO> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        return newsList.stream()
                .map(news -> new NewsDTO(
                        news.getId(),
                        news.getTitle(),
                        news.getContent(),
                        news.getAuthor().getName(),
                        news.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                        news.getCreated(),
                        news.getModified()
                ))
                .collect(Collectors.toList());
    }

    public NewsDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getAuthor().getName(),
                news.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                news.getCreated(),
                news.getModified()
        );
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = new News();
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setCreated(newsDTO.getCreated());
        news.setModified(newsDTO.getModified());
        // Set Author and Tags here as well if needed
        news = newsRepository.save(news);
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getAuthor().getName(),
                news.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                news.getCreated(),
                news.getModified()
        );
    }

    public NewsDTO updateNews(Long id, NewsDTO newsDTO) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setCreated(newsDTO.getCreated());
        news.setModified(newsDTO.getModified());
        // Update Author and Tags here if needed
        news = newsRepository.save(news);
        return new NewsDTO(
                news.getId(),
                news.getTitle(),
                news.getContent(),
                news.getAuthor().getName(),
                news.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toList()),
                news.getCreated(),
                news.getModified()
        );
    }

    public void deleteNews(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        newsRepository.delete(news);
    }
}

package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.dto.NewsDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.mapper.NewsMapper;
import kz.akseleu.newsmanager.model.Author;
import kz.akseleu.newsmanager.model.News;
import kz.akseleu.newsmanager.model.Tag;
import kz.akseleu.newsmanager.repository.AuthorRepository;
import kz.akseleu.newsmanager.repository.NewsRepository;
import kz.akseleu.newsmanager.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final AuthorRepository authorRepository;
    private final TagRepository tagRepository;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsService(NewsRepository newsRepository, AuthorRepository authorRepository,
                       TagRepository tagRepository, NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.newsMapper = newsMapper;
    }

    @Transactional(readOnly = true)
    public List<News> getAllNews() {
        List<News> newsList = newsRepository.findAll();
        newsList.forEach(news -> Hibernate.initialize(news.getTags())); // Инициализация ленивой коллекции
        return newsList;
    }

    @Transactional(readOnly = true)
    public News getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("News not found with id: " + id));
        Hibernate.initialize(news.getTags());
        return news;
    }

    @Transactional
    public News createNews(News news) {
        if (news.getAuthor() != null) {
            Author author = authorRepository.findById(news.getAuthor().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found with id: " + news.getAuthor().getId()));
            news.setAuthor(author);
        }
        return newsRepository.save(news);
    }

    @Transactional
    public News updateNews(Long id, News updatedNews) {
        News existingNews = newsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("News not found with id: " + id));

        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        existingNews.setAuthor(updatedNews.getAuthor());
        existingNews.setTags(updatedNews.getTags());

        return newsRepository.save(existingNews);
    }

    @Transactional
    public void deleteNews(Long id) {
        if (!newsRepository.existsById(id)) {
            throw new IllegalArgumentException("News not found with id: " + id);
        }
        newsRepository.deleteById(id);
    }
}

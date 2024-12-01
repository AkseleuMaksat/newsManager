package kz.akseleu.newsmanager.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@Data
public class NewsDTO {

    private Long id;
    private String title;
    private String content;
    private String authorName; // Имя автора
    private List<String> tagNames; // Список тегов
    private LocalDateTime created;
    private LocalDateTime modified;

    public NewsDTO(Long id, String title, String content, String authorName, List<String> tagNames, LocalDateTime created, LocalDateTime modified) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authorName = authorName;
        this.tagNames = tagNames;
        this.created = created;
        this.modified = modified;
    }
}

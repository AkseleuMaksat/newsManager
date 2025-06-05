package kz.akseleu.newsmanager.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Data
public class NewsDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Long authorId;
    private Set<Long> tagIds;


}

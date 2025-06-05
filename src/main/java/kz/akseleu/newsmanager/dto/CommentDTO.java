package kz.akseleu.newsmanager.dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class CommentDTO {
        private Long id;
        private String content;
        private LocalDateTime created;
        private Long newsId;
}

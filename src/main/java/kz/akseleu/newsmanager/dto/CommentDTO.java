package kz.akseleu.newsmanager.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
public class CommentDTO {

        private Long id;
        private String content;
        private LocalDateTime created;
        private LocalDateTime modified;

        public CommentDTO(Long id, String content, LocalDateTime created, LocalDateTime modified) {
            this.id = id;
            this.content = content;
            this.created = created;
            this.modified = modified;
        }
}

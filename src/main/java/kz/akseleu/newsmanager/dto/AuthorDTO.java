package kz.akseleu.newsmanager.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Data
@Getter
public class AuthorDTO {
    private Long id;
    private String name;
    private List<String> newsTitles; // Список только названий новостей

    public AuthorDTO(Long id, String name, List<String> newsTitles) {
        this.id = id;
        this.name = name;
        this.newsTitles = newsTitles;
    }
}

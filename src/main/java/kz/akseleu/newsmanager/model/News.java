package kz.akseleu.newsmanager.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false, length = 5000)
   private String content;

   @Column(nullable = false)
   private LocalDateTime created = LocalDateTime.now();

   @Column
   private LocalDateTime modified;

   @ManyToOne
   private Author author;

   @ManyToMany
   private Set<Tag> tags;

   @OneToMany(mappedBy = "news")
   private Set<Comment> comments;
}

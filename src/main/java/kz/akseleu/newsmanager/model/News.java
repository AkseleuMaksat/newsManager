package kz.akseleu.newsmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "news")
public class News {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(nullable = false)
   private String title;

   @Column(nullable = false, columnDefinition = "TEXT")
   private String content;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "author_id", nullable = false)
   private Author author;

   @Column(nullable = false)
   private LocalDateTime created;

   @Column(nullable = false)
   private LocalDateTime modified;

   @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Comment> comments;

   @ManyToMany
   @JoinTable(
           name = "news_tag",
           joinColumns = @JoinColumn(name = "news_id"),
           inverseJoinColumns = @JoinColumn(name = "tag_id")
   )
   private List<Tag> tags;
}

package kz.akseleu.newsmanager.controller;

import kz.akseleu.newsmanager.dto.TagDTO;
import kz.akseleu.newsmanager.model.Tag;
import kz.akseleu.newsmanager.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/tags")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping
    public List<TagDTO> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagDTO> getTagById(@PathVariable Long id) {
        TagDTO tagDTO = tagService.getTagById(id);
        return ResponseEntity.ok(tagDTO);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO newTagDTO = tagService.createTag(tagDTO);
        return ResponseEntity.ok(newTagDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagDTO> updateTag(@PathVariable Long id, @RequestBody TagDTO tagDTO) {
        TagDTO updatedTagDTO = tagService.updateTag(id, tagDTO);
        return ResponseEntity.ok(updatedTagDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}

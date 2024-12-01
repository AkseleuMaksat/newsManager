package kz.akseleu.newsmanager.service;

import kz.akseleu.newsmanager.dto.TagDTO;
import kz.akseleu.newsmanager.exeption.ResourceNotFoundException;
import kz.akseleu.newsmanager.model.Tag;
import kz.akseleu.newsmanager.model.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {
    private TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream()
                .map(tag -> new TagDTO(tag.getId(), tag.getName()))
                .collect(Collectors.toList());
    }

    public TagDTO getTagById(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        return new TagDTO(tag.getId(), tag.getName());
    }

    public TagDTO createTag(TagDTO tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        tag = tagRepository.save(tag);
        return new TagDTO(tag.getId(), tag.getName());
    }

    public TagDTO updateTag(Long id, TagDTO tagDTO) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tag.setName(tagDTO.getName());
        tag = tagRepository.save(tag);
        return new TagDTO(tag.getId(), tag.getName());
    }

    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found"));
        tagRepository.delete(tag);
    }
}

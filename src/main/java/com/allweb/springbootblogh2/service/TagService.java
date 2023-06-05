package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.entity.Tag;
import com.allweb.springbootblogh2.repository.TagRepository;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public List<Tag> getAllTags() {
    List<Tag> tagList = tagRepository.findAll();
    return tagList;
  }

  public Tag getById(Long id) {
    Optional<Tag> tag = tagRepository.findById(id);
    if (tag.isPresent()) {
      return tag.get();
    } else {
      throw new DataNotFoundException(
          MessageFormat.format("Tag id {0} not found", String.valueOf(id)));
    }
  }

  public Tag createOrUpdate(Tag tagRequest) {
    Optional<Tag> existingTag = tagRepository.findById(tagRequest.getId());

    if (existingTag.isPresent()) {
      Tag tagUpdate = existingTag.get();

      tagUpdate.setName(tagRequest.getName());

      return tagRepository.save(tagUpdate);
    } else {
      return tagRepository.save(tagRequest);
    }
  }

  public void deleteById(Long id) {
    Optional<Tag> tag = tagRepository.findById(id);
    if (tag.isPresent()) {
      tagRepository.deleteById(id);
    } else {
      throw new BadRequestException("Delete error, please check ID and try again");
    }
  }
}

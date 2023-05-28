package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.Tag;
import com.allweb.springbootblogh2.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagService {

  @Autowired
  TagRepository repository;

  @Autowired
  TagRepository tagRepository;

  public List<Tag> getAllTags() {
    List<Tag> tagList = repository.findAll();
    return tagList;
  }

  public Tag getById(Long id) {
    Optional<Tag> tag = repository.findById(id);
    if (tag.isPresent()) {
      return tag.get();
    } else {
      throw new DataNotFoundException(MessageFormat.format("Tag id {0} not found", String.valueOf(id)));
    }
  }

  public Tag createOrUpdate(Tag tagRequest) {
    Optional<Tag> existingTag = repository.findById(tagRequest.getId());

    if (existingTag.isPresent()) {
      Tag tagUpdate = existingTag.get();

      tagUpdate.setName(tagRequest.getName());

      return repository.save(tagUpdate);
    } else {
      return repository.save(tagRequest);
    }
  }

  public void deleteById(Long id) {
    Optional<Tag> tag = repository.findById(id);
    if (tag.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new BadRequestException("Delete error, please check ID and try again");
    }
  }

}
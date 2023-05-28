package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.Author;
import com.allweb.springbootblogh2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorService {

  @Autowired
  AuthorRepository repository;


  public List<Author> getAllAuthors() {
    List<Author> authorList = repository.findAll();
    return authorList;
  }

  public Author getById(Long id) {
    Optional<Author> author = repository.findById(id);
    if (author.isPresent()) {
      return author.get();
    } else {
      throw new DataNotFoundException(MessageFormat.format("Author id {0} not found", String.valueOf(id)));
    }
  }

  public Author createOrUpdate(Author authorRequest) {
    Optional<Author> existingAuthor = repository.findById(authorRequest.getId());

    if (existingAuthor.isPresent()) {
      Author authorUpdate = existingAuthor.get();

      authorUpdate.setName(authorRequest.getName());

      return repository.save(authorUpdate);
    } else {
      return repository.save(authorRequest);
    }
  }

  public void deleteById(Long id) {
    Optional<Author> author = repository.findById(id);
    if (author.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new BadRequestException("Delete error, please check ID and try again");
    }
  }

}

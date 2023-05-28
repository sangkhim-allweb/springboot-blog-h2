package com.allweb.springbootblogh2.controller;

import com.allweb.springbootblogh2.model.Author;
import com.allweb.springbootblogh2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
@CrossOrigin
public class AuthorController {

  @Autowired
  AuthorService service;

  @GetMapping
  public ResponseEntity<List<Author>> getAllAuthors() {
    List<Author> list = service.getAllAuthors();
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
    Author entity = service.getById(id);
    return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Author> createOrUpdate(@RequestBody Author Author) {
    Author updated = service.createOrUpdate(Author);
    return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    service.deleteById(id);
  }

}
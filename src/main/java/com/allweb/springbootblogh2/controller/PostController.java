package com.allweb.springbootblogh2.controller;

import com.allweb.springbootblogh2.model.Post;
import com.allweb.springbootblogh2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
@CrossOrigin
public class PostController {

  @Autowired
  PostService service;

  @GetMapping
  public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) String title) {
    List<Post> list = service.getAllPosts(title);
    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
    Post entity = service.getById(id);
    return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Post> createOrUpdate(@RequestBody Post post) {
    Post updated = service.createOrUpdate(post);
    return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    service.deleteById(id);
  }

}

package com.allweb.springbootblogh2.controller;

import com.allweb.springbootblogh2.model.entity.Post;
import com.allweb.springbootblogh2.model.entity.Tag;
import com.allweb.springbootblogh2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/blogs")
@CrossOrigin
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("/v1/blogs")
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) String title) {
        List<Post> list = service.getAllPosts(title);
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/v1/blogs/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") Long id) {
        Post entity = service.getById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/v1/blogs")
    public ResponseEntity<Post> createOrUpdate(@RequestBody Post post) {
        Post updated = service.createOrUpdate(post);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/v1/blogs/{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagsByPostId(@PathVariable(value = "id") Long id) {
        List<Tag> tagList = service.getAllTagsByPostId(id);
        return new ResponseEntity<>(tagList, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/v1/blogs/{id}/tags")
    public ResponseEntity<Tag> addTag(@PathVariable("id") Long id, @RequestBody Tag tagRequest) {
        Tag updated = service.addTag(id, tagRequest);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/v1/blogs/{id}/tags/{tagId}")
    public void deleteTagFromPost(@PathVariable("id") Long id, @PathVariable("tagId") Long tagId) {
        service.deleteTagFromPost(id, tagId);
    }

    @DeleteMapping("/v1/blogs/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}

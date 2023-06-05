package com.allweb.springbootblogh2.controller;

import com.allweb.springbootblogh2.model.entity.Tag;
import com.allweb.springbootblogh2.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/tags")
@CrossOrigin
public class TagController {

    @Autowired
    TagService service;

    @GetMapping("/v1/tags")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> list = service.getAllTags();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/v1/tags/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable("id") Long id) {
        Tag entity = service.getById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/v1/tags")
    public ResponseEntity<Tag> createOrUpdate(@RequestBody Tag Tag) {
        Tag updated = service.createOrUpdate(Tag);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/v1/tags/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

}

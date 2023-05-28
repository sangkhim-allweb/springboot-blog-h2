package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.Post;
import com.allweb.springbootblogh2.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostService {

  @Autowired
  PostRepository repository;

  public List<Post> getAllPosts() {
    List<Post> postList = repository.findAll();
    return postList;
  }

  public Post getById(Long id) {
    Optional<Post> post = repository.findById(id);
    if (post.isPresent()) {
      return post.get();
    } else {
      throw new DataNotFoundException(MessageFormat.format("Post id {0} not found", String.valueOf(id)));
    }
  }

  public Post createOrUpdate(Post postRequest) {
    Optional<Post> existingPost = repository.findById(postRequest.getId());

    if (existingPost.isPresent()) {
      Post postUpdate = existingPost.get();

      postUpdate.setTitle(postRequest.getTitle());
      postUpdate.setBody(postRequest.getBody());

      // save foreign key
      postUpdate.setAuthor(postRequest.getAuthor());

      return repository.save(postUpdate);
    } else {
      return repository.save(postRequest);
    }
  }

  public void deleteById(Long id) {
    Optional<Post> post = repository.findById(id);
    if (post.isPresent()) {
      repository.deleteById(id);
    } else {
      throw new BadRequestException("Delete error, please check ID and try again");
    }
  }

}

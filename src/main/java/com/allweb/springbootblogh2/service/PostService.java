package com.allweb.springbootblogh2.service;

import com.allweb.springbootblogh2.exception.BadRequestException;
import com.allweb.springbootblogh2.exception.DataNotFoundException;
import com.allweb.springbootblogh2.model.Post;
import com.allweb.springbootblogh2.model.Tag;
import com.allweb.springbootblogh2.repository.PostRepository;
import com.allweb.springbootblogh2.repository.TagRepository;
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

  @Autowired
  TagRepository tagRepository;

  public List<Post> getAllPosts(String title) {
    List<Post> postList;
    if (title == null) {
      postList = repository.findAll();
    } else {
      postList = repository.findByTitleContaining(title);
    }
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

  public Tag createOrUpdateTag(Long postId, Tag tagRequest) {
    Tag tag = repository.findById(postId).map(post -> {

      Optional<Tag> _tag = tagRepository.findById(tagRequest.getId());
      if (tagRequest.getId() != 0) {
        if (_tag.isPresent()) {
          post.addTag(_tag.get());
          repository.save(post);
          return _tag.get();
        } else {
          throw new DataNotFoundException(MessageFormat.format("Tag id {0} not found", String.valueOf(tagRequest.getId())));
        }
      } else {
        // create new tag
        post.addTag(tagRequest);
        return tagRepository.save(tagRequest);
      }

    }).orElseThrow(() -> new DataNotFoundException(MessageFormat.format("Post id {0} not found", String.valueOf(postId))));

    return tag;
  }

  public void deleteTagFromPost(Long postId, Long tagId) {
    Optional<Post> post = repository.findById(postId);
    if (post.isPresent()) {
      post.get().removeTag(tagId);
      repository.save(post.get());
    } else {
      throw new BadRequestException("Delete error, please check ID and try again");
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

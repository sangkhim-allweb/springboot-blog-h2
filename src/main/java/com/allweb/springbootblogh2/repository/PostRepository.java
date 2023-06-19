package com.allweb.springbootblogh2.repository;

import com.allweb.springbootblogh2.model.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTitleContaining(String title);

    @Modifying
    @Query(value = "SELECT * FROM posts WHERE title LIKE %:title%", nativeQuery = true)
    List<Post> selectPosts(@Param("title") String title);

    @Query(
            value = "SELECT * FROM Users ORDER BY id",
            countQuery = "SELECT count(*) FROM Users",
            nativeQuery = true)
    Page<Post> findAllPostsWithPagination(Pageable pageable);

    @Modifying
    @Query("DELETE FROM Post p WHERE p.title = :title OR p.body = :body")
    int deletePosts(@Param("title") String title, @Param("body") String body);

}

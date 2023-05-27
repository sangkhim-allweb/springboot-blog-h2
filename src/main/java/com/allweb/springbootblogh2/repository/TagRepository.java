package com.allweb.springbootblogh2.repository;

import com.allweb.springbootblogh2.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}

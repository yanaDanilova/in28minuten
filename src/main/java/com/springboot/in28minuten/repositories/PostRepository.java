package com.springboot.in28minuten.repositories;

import com.springboot.in28minuten.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}

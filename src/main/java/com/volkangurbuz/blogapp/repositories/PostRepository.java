package com.volkangurbuz.blogapp.repositories;

import com.volkangurbuz.blogapp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}

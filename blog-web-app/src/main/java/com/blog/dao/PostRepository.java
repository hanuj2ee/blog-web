package com.blog.dao;

import com.blog.client.pojo.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to interact boiler plate code
 */
public interface PostRepository extends JpaRepository<Post, String> {
}

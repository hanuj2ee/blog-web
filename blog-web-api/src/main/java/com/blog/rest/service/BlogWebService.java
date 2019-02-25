package com.blog.rest.service;

import com.blog.client.pojo.ClientResponse;
import com.blog.client.pojo.Post;
import com.blog.client.pojo.PostDto;
import org.springframework.http.ResponseEntity;

/**
 * BlogWebService
 */
public interface BlogWebService {

    /**
     *
     * @return
     */
    ResponseEntity<PostDto> getAllPosts();

    /**
     *
     * @param body
     * @return
     */
    ResponseEntity<ClientResponse> addPost(Post body);

    /**
     *
     * @param body
     * @return
     */
    ResponseEntity<ClientResponse> updatePost(Post body);

    /**
     *
     * @param postId
     * @return
     */
    ResponseEntity<Post> getPostById(String postId);

    /**
     *
     * @param postId
     * @return
     */
    ResponseEntity<ClientResponse> deletePost(String postId);

}

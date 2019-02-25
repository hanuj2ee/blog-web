package com.blog.rest.service;

import com.blog.dao.PostRepository;
import com.blog.client.pojo.ClientResponse;
import com.blog.client.pojo.Post;
import com.blog.client.pojo.PostDto;
import com.blog.rest.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * BlogWebServiceImpl
 */
@Service
public class BlogWebServiceImpl implements BlogWebService {
    /**
     * class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(BlogWebServiceImpl.class);

    /**
     * Post db repository
     */
    private PostRepository postRepository;

    @Autowired
    public BlogWebServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<PostDto> getAllPosts() {
        LOG.info("getAllPosts entering");

        List<Post> list = postRepository.findAll();
        PostDto dto = new PostDto();
        dto.setPosts(list);
        ResponseEntity response = new ResponseEntity(dto, HttpStatus.OK);

        LOG.info("getAllPosts exiting. Returning response : {}", response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ClientResponse> addPost(Post body) {
        LOG.info("addPost entering {}", body);

        try {
            Validator.validatePost(body);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
        }
        ClientResponse obj = new ClientResponse();
        body.setId(UUID.randomUUID().toString());
        postRepository.saveAndFlush(body);

        obj.setMessage("ADDED");
        ResponseEntity response = new ResponseEntity(obj, HttpStatus.CREATED);

        LOG.info("addPost exiting. Returning response : {}", response);
        return response;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ClientResponse> updatePost(Post body) {
        try {
            Validator.validatePost(body);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
        }

        ClientResponse obj = new ClientResponse();
        boolean isAvailable = postRepository.existsById(body.getId());
        if(!isAvailable) {
            obj.setMessage("Post not found");
            return new ResponseEntity(obj, HttpStatus.NOT_FOUND);
        }

        postRepository.saveAndFlush(body);
        obj.setMessage("UPDATED");
        return new ResponseEntity(obj, HttpStatus.CREATED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<Post> getPostById(String postId) {
        try {
            Validator.validatePostId(postId);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
        }
        Optional<Post> obj = postRepository.findById(postId);
        if(obj.isPresent()) {
            return new ResponseEntity(obj.get(), HttpStatus.OK);
        }
        return new ResponseEntity("No Content", HttpStatus.NO_CONTENT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseEntity<ClientResponse> deletePost(String postId) {
        try {
            Validator.validatePostId(postId);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity("Invalid input", HttpStatus.METHOD_NOT_ALLOWED);
        }
        boolean isAvailable = postRepository.existsById(postId);
        ClientResponse obj = new ClientResponse();
        if(isAvailable) {
            postRepository.deleteById(postId);
            obj.setMessage("DELETED");
            return new ResponseEntity(obj, HttpStatus.OK);
        } else {
            obj.setMessage("Post not found");
            return new ResponseEntity(obj, HttpStatus.NOT_FOUND);
        }
    }
}

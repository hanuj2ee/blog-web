package com.blog.rest.controller;

import com.blog.client.pojo.ClientResponse;
import com.blog.client.pojo.Post;
import com.blog.client.pojo.PostDto;
import com.blog.rest.service.BlogWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * BlogWebController
 */
@RestController
public class BlogWebController {

    /**
     * class logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(BlogWebController.class);

    /**
     * service class
     */
    private BlogWebService blogWebService;

    @Autowired
    public BlogWebController(BlogWebService blogWebService) {
        this.blogWebService = blogWebService;
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/hello-pierce", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ClientResponse> ping() {
        ClientResponse obj = new ClientResponse();
        obj.setMessage("hello-pierce");
        return new ResponseEntity(obj, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping(value = "/posts", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<PostDto> getAllPosts() {
        return blogWebService.getAllPosts();
    }

    /**
     *
     * @param body
     * @return
     */
    @PostMapping(value = "/posts",
            produces = {APPLICATION_JSON_UTF8_VALUE},
            consumes = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ClientResponse> addPost(@Valid @RequestBody Post body) {
        return blogWebService.addPost(body);
    }

    /**
     *
     * @param body
     * @return
     */
    @PutMapping(value = "/posts",
            produces = {APPLICATION_JSON_UTF8_VALUE},
            consumes = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ClientResponse> updatePost(@Valid @RequestBody Post body) {
        LOG.info("updatePost entering {}", body);

        ResponseEntity response = blogWebService.updatePost(body);

        LOG.info("updatePost exiting. Returning response : {}", response);
        return response;
    }

    /**
     *
     * @param postId
     * @return
     */
    @GetMapping(value = "/posts/{postId}",
            produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<Post> getPostById(@PathVariable String postId) {
        LOG.info("getPostById entering {}", postId);

        ResponseEntity response = blogWebService.getPostById(postId);

        LOG.info("getPostById exiting. Returning response : {}", response);
        return response;
    }

    /**
     *
     * @param postId
     * @return
     */
    @DeleteMapping(value = "/posts", produces = {APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<ClientResponse> deletePost(@RequestParam("postId") String postId) {
        LOG.info("deletePost entering {}", postId);

        ResponseEntity response = blogWebService.deletePost(postId);

        LOG.info("deletePost exiting. Returning response : {}", response);
        return response;
    }
}

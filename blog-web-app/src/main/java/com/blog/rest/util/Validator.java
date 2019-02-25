package com.blog.rest.util;

import com.blog.client.pojo.Post;
import org.springframework.util.Assert;

/**
 * Validates payload
 */
public class Validator {

    /**
     *
     * @param body
     */
    public static void validatePost(Post body) {
        Assert.notNull(body.getTitle(), "Invalid input, title can not be null");
        Assert.hasLength(body.getTitle(), "Invalid input, title can not be Empty");
        Assert.notNull(body.getContent(), "Invalid input, content can not be null");
        Assert.hasLength(body.getContent(), "Invalid input, content can not be Empty");
    }

    /**
     *
     * @param body
     */
    public static void validatePostId(String postId) {
        Assert.notNull(postId, "Invalid input, postId can not be null");
        Assert.hasLength(postId, "Invalid input, postId can not be Empty");
    }

}

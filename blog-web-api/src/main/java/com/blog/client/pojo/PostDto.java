package com.blog.client.pojo;

import java.util.List;

/**
 *
 */
public class PostDto {
    private List<Post> posts;

    public PostDto(){}

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}

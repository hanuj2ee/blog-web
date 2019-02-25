package com.blog.rest.controller;

import com.blog.app.BlogWebApplication;
import com.blog.client.pojo.Post;
import com.blog.dao.PostRepository;
import com.blog.rest.service.BlogWebService;
import com.blog.rest.service.BlogWebServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= BlogWebApplication.class)
public class BlogWebControllerITTest {

    private BlogWebService service;

    private MockMvc mvc;

    @Mock
    private PostRepository postRepository;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        service = new BlogWebServiceImpl(postRepository);
        mvc = MockMvcBuilders.standaloneSetup(new BlogWebController(service)).build();
    }

    @Test
    public void ping() throws Exception {
        mvc.perform(get("/hello-pierce").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getAllPosts() throws Exception {
        mvc.perform(get("/posts").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void getPostById() throws Exception {
        mvc.perform(get("/posts/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void deletePost() throws Exception {
        mvc.perform(delete("/posts?postId=1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void addPost() throws Exception {
        mvc.perform(post("/posts").content(getDummyPostAsJson()).contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(HttpStatus.CREATED.value()));
    }

    private String getDummyPostAsJson() {
        Post post = new Post();
        post.setTitle("Title");
        post.setContent("Content");

        String jsonInString = null;
        //Convert object to JSON string
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(post);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

}

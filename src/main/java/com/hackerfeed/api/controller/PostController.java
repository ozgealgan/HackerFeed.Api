package com.hackerfeed.api.controller;

import com.hackerfeed.api.domain.Post;
import com.hackerfeed.api.exception.NotFoundException;
import com.hackerfeed.api.model.request.PostRequest;
import com.hackerfeed.api.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void CreatePost(@RequestBody PostRequest request)
    {
        postService.CreatePost(request);
    }

    @GetMapping
    public List<Post> GetAllPosts()
    {
        return postService.GetAllPosts();
    }

    @GetMapping("/{id}")
    public Post GetPost(@PathVariable UUID id) throws NotFoundException {
        return postService.GetPost(id);
    }

    @DeleteMapping("/{id}")
    public void DeletePost(@PathVariable UUID id) throws NotFoundException {
         postService.DeletePost(id);
    }

    @PutMapping("/{id}")
    public void UpdatePost(@RequestBody PostRequest request, @PathVariable UUID id) throws NotFoundException {
        postService.UpdatePost(request, id);
    }

}

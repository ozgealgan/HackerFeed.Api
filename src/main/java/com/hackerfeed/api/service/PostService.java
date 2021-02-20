package com.hackerfeed.api.service;

import com.hackerfeed.api.domain.Post;
import com.hackerfeed.api.exception.NotFoundException;
import com.hackerfeed.api.model.request.PostRequest;
import com.hackerfeed.api.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void CreatePost(PostRequest request){
        var post = new Post();
        post.setLink(request.getLink());
        post.setUserName(request.getUserName());
        post.setTitle(request.getTitle());
        post.setCreationDate(new Date());

        postRepository.save(post);
    }

    public List<Post> GetAllPosts()
    {
        return postRepository.findAll();
    }

    public Post GetPost(UUID id) throws NotFoundException {
        var post =  postRepository.findById(id);

        if(post.isEmpty()){
            throw new NotFoundException("Post in not found for id:" + id.toString());
        }

        return post.get();
    }

    public void DeletePost(UUID id) throws NotFoundException {
        var post =  postRepository.findById(id);

        if(post.isEmpty()){
            throw new NotFoundException("Post in not found for id:" + id.toString());
        }

        post.get().setIsDeleted(true);
        postRepository.save(post.get());
    }

    public void UpdatePost(PostRequest request, UUID id) throws NotFoundException {
        var post =  postRepository.findById(id);

        if(post.isEmpty()){
            throw new NotFoundException("Post in not found for id:" + id.toString());
        }

        post.get().setLink(request.getLink());
        post.get().setTitle(request.getTitle());

        postRepository.save(post.get());
    }
}

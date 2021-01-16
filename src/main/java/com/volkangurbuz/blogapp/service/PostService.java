package com.volkangurbuz.blogapp.service;

import com.volkangurbuz.blogapp.exception.PostNotFoundException;
import com.volkangurbuz.blogapp.model.Post;
import com.volkangurbuz.blogapp.repositories.PostRepository;
import dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {

  @Autowired private AuthService authService;
  @Autowired private PostRepository postRepository;

  public void createPost(PostDto postDto) throws Throwable {
    Post post = mapFromDtoToPost(postDto);
    postRepository.save(post);
  }

  private PostDto mapFromPostToDto(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setContent(post.getContent());
    postDto.setUsername(post.getUsername());
    return postDto;
  }

  public List<PostDto> showAllPosts() {
    List<Post> posts = postRepository.findAll();
    // map to mapfromposttodto
    return posts.stream().map(this::mapFromPostToDto).collect(toList());
  }

  public PostDto readSinglePost(Long id) {
    Post post =
        postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
    return mapFromPostToDto(post);
  }

  private Post mapFromDtoToPost(PostDto postDto) throws Throwable {
    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setContent(postDto.getContent());
    User loggedInUser =
        (User)
            authService
                .getCurrentUser()
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));
    post.setCreatedOn(Instant.now());
    post.setUsername(loggedInUser.getUsername());
    post.setUpdatedOn(Instant.now());
    return post;
  }
}

package com.aa.coolreads.User.mappers;

import com.aa.coolreads.User.dto.PostCreationDTO;
import com.aa.coolreads.User.dto.PostDTO;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostDTO toPostDTO(Post post) {
        return new PostDTO(post.getTitle(), post.getPostContent(), post.getId());
    }

    public Post toPost(PostCreationDTO postDTO, Customer customer) {
        return new Post(postDTO.getTitle(), postDTO.getContent(), customer);
    }
}

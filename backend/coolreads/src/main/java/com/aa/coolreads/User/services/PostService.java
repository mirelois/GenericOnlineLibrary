package com.aa.coolreads.User.services;

import com.aa.coolreads.User.dto.PostCreationDTO;
import com.aa.coolreads.User.dto.PostDTO;
import com.aa.coolreads.User.exception.CustomerNotFoundException;
import com.aa.coolreads.User.mappers.PostMapper;
import com.aa.coolreads.User.models.Customer;
import com.aa.coolreads.User.models.Post;
import com.aa.coolreads.User.repositories.CustomerRepository;
import com.aa.coolreads.User.repositories.PostRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    private final CustomerRepository customerRepository;

    private final PostMapper postMapper;

    public PostService(PostRepository postRepository, CustomerRepository customerRepository, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.customerRepository = customerRepository;
        this.postMapper = postMapper;
    }

    @Transactional
    public void insertPost(PostCreationDTO postCreationDTO, String username) throws CustomerNotFoundException {

        Customer customer = this.customerRepository.findById(username).orElseThrow(() -> new CustomerNotFoundException(username));

        this.postRepository.save(this.postMapper.toPost(postCreationDTO, customer));
    }

    @Transactional
    public Set<PostDTO> getPostsByUsername(String username, Integer pageNumber, Integer pageSize) {

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> postPage = this.postRepository.findByCustomerUsername(username, pageable);

        return postPage.get().map(this.postMapper::toPostDTO).collect(Collectors.toSet());
    }

    @Transactional
    public void deletePost(Long postId) {

        this.postRepository.deleteById(postId);

    }

}

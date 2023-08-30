package com.springboot.in28minuten.controllers;

import com.springboot.in28minuten.entities.Post;
import com.springboot.in28minuten.entities.User;
import com.springboot.in28minuten.repositories.PostRepository;
import com.springboot.in28minuten.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User with id "+ id+" is not found");
        }
        return userOptional.get();
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @PostMapping
    public User createNewUser(@RequestBody User user){
      return userRepository.save(user);
    }

/*    @PutMapping
    public User updateExistingUser(@RequestBody User user){
       return userRepository.findById(user.getId());

    }*/


    @GetMapping("/{id}/posts")
    public List<Post> getPostsForUser(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with id "+ id+" is not found");
        }
        return optionalUser.get().getPosts();

    }
    @PostMapping("/{id}/posts")
    public Post createNewPostForUser(@PathVariable(name = "id") Long userId, @RequestBody Post post){
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new RuntimeException("User with id "+ userId +" is not found");
        }
        post.setUser(optionalUser.get());
        return postRepository.save(post);
    }



}

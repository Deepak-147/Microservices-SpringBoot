package com.ldeepak.springboot.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ldeepak.springboot.restfulwebservices.jpa.PostRepository;
import com.ldeepak.springboot.restfulwebservices.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	private UserRepository userRepository;
	private PostRepository postRepository;
	
	public UserResource(UserRepository userRepository, PostRepository postRepository) {
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}
	
	// GET /users
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	// GET /users/{id}
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " is not found.");
		}
		
		return user.get();
	}
	
	// POST /users
	// Validate the request body
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User createdUser = userRepository.save(user);

		// Best practice is to return URI location of newly created resource.
		// It can be found under Headers tab -> Location in postman tool.
		// URI should be like /users/{id}
		// /users/4 => /users/ /{id}, user.getId()
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		
		// Best practice is to return a status of "Created", along with status code. It
		// can be found where Status is displayed in postman tool.
		return ResponseEntity.created(location).build();
	}
	
	// DELETE /users/{id}
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	// Get /users/{id}/posts
	@GetMapping("/users/{id}/posts")
	public List<Post> retrieveAllPostsForAUser(@PathVariable int id) {
		
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " is not found.");
		}
		
		return user.get().getPosts();
		
	}
	
	// POST /users/{id}/posts
	// Validate the request body
	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		
		Optional<User> user = userRepository.findById(id);
		
		if (user.isEmpty()) {
			throw new UserNotFoundException("User with id: " + id + " is not found.");
		}
		
		post.setUser(user.get());
		
		Post createdPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(createdPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
}

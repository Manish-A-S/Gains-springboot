package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.entity.User_Credentials;
import com.example.demo.repo.CredentialsRepository;
import com.example.demo.repo.DoubtsRepository;
import com.example.demo.repo.NotesRepository;
import com.example.demo.repo.QuizRepository;
import com.example.demo.repo.TopicRepository;
import com.example.demo.repo.TutorRepository;
import com.example.demo.repo.UserRepository;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ResourceController {
	
	@Autowired
	CredentialsRepository credRepo;
	@Autowired
	DoubtsRepository doubtRepo;
	@Autowired
	NotesRepository notesRepo;
	@Autowired
	QuizRepository quizRepo;
	@Autowired
	TopicRepository topicRepo;
	@Autowired
	TutorRepository tutorRepo;
	@Autowired
	UserRepository userRepo;
	
	public ResourceController(CredentialsRepository credRepo, DoubtsRepository doubtRepo, NotesRepository notesRepo,
			QuizRepository quizRepo, TopicRepository topicRepo, TutorRepository tutorRepo, UserRepository userRepo) {
		super();
		this.credRepo = credRepo;
		this.doubtRepo = doubtRepo;
		this.notesRepo = notesRepo;
		this.quizRepo = quizRepo;
		this.topicRepo = topicRepo;
		this.tutorRepo = tutorRepo;
		this.userRepo = userRepo;
	}

	//For Creating Users
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/create-credentials")
	public String createCredentials(@RequestBody User_Credentials credentials) {
		System.out.println(credentials);
		Optional<User_Credentials> cred=credRepo.findByEmail(credentials.getEmail());
		if(cred.isPresent()) {
			return "User already exists";
		}
		
		credRepo.save(credentials);
		return "Account created";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/create")
	public String create(@RequestBody User_Credentials d) {
		
		System.out.println(d);
		return "Account created";
	}
	
	//For Authentication(Login)
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/login")
	public List<String> retriveCredentials(@RequestBody User_Credentials credentials){
		List<String> load = new ArrayList<String>();
		Optional<User_Credentials> user=credRepo.findByEmail(credentials.getEmail());
		if(user.isEmpty()) {
			load.add("User not found");	
			return load;
		}

		if(user.get().getPassword().equals(credentials.getPassword())) {
			load.add("Authorized");	
			load.add(String.valueOf(user.get().getId()));
			return load;
		}
			
		else {
			load.add("Wrong Password");	
			return load;
		}		
	}

	//For Adding users
	@CrossOrigin(origins="https://localhost:3000")
	@PostMapping("/create_user")
	public String createUsers(@RequestBody User user) {
		System.out.println(user);
		
		userRepo.save(user);
		return "Details saved";
		
	}
	
	//Get User's data using user id
	@CrossOrigin(origins="https://localhost:3000")
	@GetMapping("/retrieve_user/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		Optional<User> users = userRepo.findById(id);
		return users.get();		
	}
	
	//Edit user data in profile section
		@CrossOrigin(origins="https://localhost:3000")
		@PutMapping("/retrieve_user/{id}")
		public String editUser(@RequestBody User user,@PathVariable int id) {
			
			Optional<User> users = userRepo.findById(id);
			
			System.out.println(user);
			
			return "Updated Successfully";
		}


	
}
	
	
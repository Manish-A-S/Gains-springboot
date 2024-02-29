package com.example.demo.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Doubts;
import com.example.demo.entity.Notes;
import com.example.demo.entity.Questions;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.Topic;
import com.example.demo.entity.Tutor;
import com.example.demo.entity.User;
import com.example.demo.repo.DoubtsRepository;
import com.example.demo.repo.NotesRepository;
import com.example.demo.repo.QuestionsRepository;
import com.example.demo.repo.QuizRepository;
import com.example.demo.repo.TopicRepository;
import com.example.demo.repo.TutorRepository;
import com.example.demo.repo.UserRepository;




@RestController
public class ResourceController {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	DoubtsRepository doubtRepository;
	
	@Autowired
	NotesRepository noteRepository;
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	@Autowired
	QuestionsRepository questionRepository;
	
	
	

	
	
	
	public ResourceController(UserRepository repository, TopicRepository topicRepository,
			DoubtsRepository doubtRepository, NotesRepository noteRepository, QuizRepository quizRepository,
			TutorRepository tutorRepository, QuestionsRepository questionRepository) {
		super();
		this.repository = repository;
		this.topicRepository = topicRepository;
		this.doubtRepository = doubtRepository;
		this.noteRepository = noteRepository;
		this.quizRepository = quizRepository;
		this.tutorRepository = tutorRepository;
		this.questionRepository = questionRepository;
	}


	//Creating users along with details
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/create-users")
	public String createUser(@RequestBody User user) {
		
		Optional<User> users=repository.findByEmail(user.getEmail());
		if(users.isPresent()) {
			
			return "User already exists";
		}
		
		repository.save(user);
		return "Account created";
	}
	
	
	//Authentication
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/login")
	public List<String> retriveUser(@RequestBody User users){
		List<String> load = new ArrayList<String>();
		Optional<User> user=repository.findByEmail(users.getEmail());
		if(user.isEmpty()) {
			load.add("User not found");	
			return load;
		}

		if(user.get().getPassword().equals(users.getPassword())) {
			load.add("Authorized");	
			load.add(String.valueOf(user.get().getId()));
			return load;
		}
			
		else {
			load.add("Wrong Password");	
			return load;
		}		
	}
	
	//Get user details
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/get-users/{id}")
	public HashMap<String, String> getUser(@PathVariable int id) {
		
		Optional<User> users=repository.findById(id);
		
		HashMap<String,String> user= new HashMap<>();
		user.put("id", Integer.toString(users.get().getId()));
		user.put("email", users.get().getEmail());
		user.put("firstName", users.get().getFirstName());
		user.put("lastName", users.get().getLastName());
		user.put("phone", Long.toString(users.get().getPhone()));
		user.put("dob", users.get().getDob());
		user.put("grade", users.get().getGrade()); 
		user.put("institute", users.get().getInstitute());
		
		return user;
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping("/jpa/update-user/{id}")
	public String updateUser(@RequestBody User user,@PathVariable int id) {

	
		repository.updateUser(id,user.getDob(),user.getFirstName(),user.getLastName(),user.getGrade(),user.getInstitute(),user.getPhone());
		return "Details updated";
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-topics")
	public int createTopic(@RequestBody Topic topic,@PathVariable int id) {
		
		Optional<User> user =repository.findById(id);
		System.out.println(topic);
		topic.setUser(user.get());
		topic.setTimestamp(new Timestamp(System.currentTimeMillis()));
		topicRepository.save(topic);
		List<Topic> topics=user.get().getTopic();
		
		int len=topics.size();
		
		return topics.get(len-1).getId();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-topics")
	public List<Topic> getTopic(@PathVariable int id) {
		
		Optional<User> user =repository.findById(id);
		
		
		List<Topic> topics=user.get().getTopic();
		
		for(Topic topic : topics) {
			List<Boolean> action=new ArrayList<>();
			if(topic.getTutor().size()==0) {
				action.add(false);
			}
			else {
				action.add(true);
			}
			if(topic.getNotes().size()==0) {
				action.add(false);
			}
			else {
				action.add(true);
			}
			if(topic.getDoubts().size()==0) {
				action.add(false);
			}
			else {
				action.add(true);
			}
			if(topic.getQuiz().size()==0) {
				action.add(false);
			}
			else {
				action.add(true);
			}
			
			topic.setAction(action);
		}
		

		return topics;
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-doubt")
	public String createDoubt(@RequestBody Doubts doubt,@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);
		doubt.setTopics(topic.get());
		doubt.setTimestamp(new Timestamp(System.currentTimeMillis()));
		doubtRepository.save(doubt);
		return "Doubt saved";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-doubt")
	public List<Doubts> getDoubt(@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);
		return topic.get().getDoubts();
	}
	
	

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-quiz")
	public String createQuiz(@RequestBody Quiz quiz,@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);
		quiz.setTopics(topic.get());
		quiz.setTimestamp(new Timestamp(System.currentTimeMillis()));
		quizRepository.save(quiz);
		return "Quiz saved";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-quiz")
	public List<Quiz> getQuiz(@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);

		return topic.get().getQuiz();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-video")
	public String createVideo(@RequestBody Tutor tutor,@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);
		tutor.setTopics(topic.get());
		tutor.setTimestamp(new Timestamp(System.currentTimeMillis()));
		tutorRepository.save(tutor);
		return "video saved";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-video")
	public List<Tutor> getVideo(@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);

		return topic.get().getTutor();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-notes")
	public String createNotes(@RequestBody Notes note,@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);
		note.setTopics(topic.get());
		note.setTimestamp(new Timestamp(System.currentTimeMillis()));
		noteRepository.save(note);
		return "Notes saved";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-notes")
	public List<Notes> getNotes(@PathVariable int id) {
		
		Optional<Topic> topic =topicRepository.findById(id);

		return topic.get().getNotes();
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/{id}/create-questions")
	public String createQuestions(@RequestBody Questions question,@PathVariable int id) {
		
		Optional<Quiz> quiz =quizRepository.findById(id);
		question.setQuiz(quiz.get());
		questionRepository.save(question);
		return "Notes saved";
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/{id}/get-questions")
	public List<Questions> getQuestions(@PathVariable int id) {
		
		Optional<Quiz> quiz =quizRepository.findById(id);

		return quiz.get().getQuestions();
	}
	
	
}
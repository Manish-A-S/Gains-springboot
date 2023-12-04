package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.Recommendation;
import com.example.demo.entity.Reviews;
import com.example.demo.entity.User;
import com.example.demo.repo.RecommendationRepository;
import com.example.demo.repo.ReviewsRepository;
import com.example.demo.repo.UserRepository;




@RestController
public class ResourceController {
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	ReviewsRepository reviewsRepository;
	
	@Autowired
	RecommendationRepository recommendationRepository;
	
	public ResourceController(UserRepository repository,ReviewsRepository postRepository,RecommendationRepository recommendationRepository) {
		this.repository=repository;
		this.reviewsRepository=postRepository;
		this.recommendationRepository= recommendationRepository;
	}

	
	@CrossOrigin(origins="http://localhost:3000")
	@PutMapping("/jpa/users/{id}/edit_reviews")
	public String editReviews(@RequestBody Reviews reviews,@PathVariable int id) {
		Optional<User> user = repository.findById(id);
		int flag=0;
		
		List<Reviews> review = user.get().getReviews();
		
		for(Reviews rev:review) {
			if(rev.getMovieName().equals(reviews.getMovieName()) &&  rev.getId()==reviews.getId()) {
				flag=1;
				reviewsRepository.updateMovie(reviews.getMovieName(), reviews.getReview(), reviews.getSentiment(),reviews.getId());	
				return "Review updated";
			}
			else if(rev.getMovieName().equals(reviews.getMovieName())  &&  rev.getId()!=reviews.getId()){
				flag=1;
				return "Review for this movie exists.";
				
			}
		}
		if(flag==0) {
			reviewsRepository.updateMovie(reviews.getMovieName(), reviews.getReview(), reviews.getSentiment(),reviews.getId());	
			return "Review updated";
		}
		
		return " ";
		
		
		
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@DeleteMapping("/jpa/reviews/{id}")
	public String deleteReviews(@PathVariable int id) {
		System.out.println("Entered delete");
		reviewsRepository.deleteById(id);
		
		return "Deleted";
	}
	
	
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

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/{id}/reviews")
	public List<Reviews> retrieveReviewsForUser(@PathVariable int id){
		
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().getReviews();
	}

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/{id}/positiveMovies")
	public List<String> retrievePositiveReviewsForUser(@PathVariable int id){
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().PositiveReviews();
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/users/{id}/reviews")
	public String createReviewsForUser(@PathVariable int id,@RequestBody Reviews reviews){
//		Optional<Reviews> review = reviewsRepository.findReviewsBymovieName(reviews.getMovieName());
//		if(review.isPresent()) {
//			return "Review for this movie exists.";
//		}
//		
//		Optional<User> user=repository.findById(id);
//		if(user.isEmpty()) {
//			throw new UserNotFoundException("id:"+id);
//		}
//		reviews.setUser(user.get());
//		Reviews savedReviews=reviewsRepository.save(reviews);
		
		//
		//
		Optional<User> user = repository.findById(id);
		int flag=0;
		
		List<Reviews> review = user.get().getReviews();
		
		for(Reviews rev:review) {
			if(rev.getMovieName().equals(reviews.getMovieName())) {
				flag=1;	
				return "Review for this movie exists.";
			}
		}
		if(flag==0) {
			reviews.setUser(user.get());
			reviewsRepository.save(reviews);
			return "Review created";
		}
		
		return " ";
		
		
	}

	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/{id}/recommendation")
	public List<String> retrieveRecommendationForUser(@PathVariable int id){
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().recommendation();
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/users/{id}/recommendation")
	public String createRecommendationForUser(@PathVariable int id,@RequestBody List<Recommendation> recommendations){
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		List<Recommendation> movie = recommendationRepository.findByUser(user);
        recommendationRepository.deleteAll(movie);
		
		for(Recommendation i:recommendations) {
			i.setUser(user.get());
			Recommendation savedRecommendation=recommendationRepository.save(i);
		}
		
		
		
		
		return "Success";
	}
	
//	@CrossOrigin(origins="http://localhost:3000")
//	@GetMapping("/jpa/users/{id}/allPositiveMovies")
//	public Map<Integer, List<String>> retrieveMoviesofAllUsers(@PathVariable int id){
//		List<User> users=repository.findAll();
//		Map<Integer,List<String>> movie = new HashMap<>();
//		List<Integer> ids = users.stream()
//                .map(User::getId)
//                .collect(Collectors.toList());
//		for(Integer i:ids) {
//			Optional<User> user=repository.findById(i);
//			List<String> names = user.get().PositiveReviews();
//			if(names.size()>0 && id!=i)
//				movie.put(i, names);
//		}
//		return movie;
//	}
	
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
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/movienames")
	public List<String> retrieveMovieNames(){
		
		List<String> movies = reviewsRepository.getAllMovieNames();
		return movies;
	}
	
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/movies")
	public Map<Integer, List<Reviews>> retrieveMovies(){
		List<Integer> ids= repository.getIds();
		Map<Integer,List<Reviews>> movie =new HashMap<>();
		
		for(Integer i:ids) {
			Optional<User> user=repository.findById(i);
			List<Reviews> names = user.get().getReviews();
			if(names.size()>0)
				movie.put(i, names);
		}
		
		
		return movie;
	}
	
	
}

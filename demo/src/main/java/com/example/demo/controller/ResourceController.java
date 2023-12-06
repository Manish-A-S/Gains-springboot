package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
	@PostMapping("/jpa/users/{id}/reviews")
	public String createReviews(@PathVariable int id,@RequestBody Reviews reviews){
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
	@GetMapping("/jpa/users/{id}/reviews")
	public List<Reviews> retrieveReviewsForUser(@PathVariable int id){
		
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		return user.get().getReviews();
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/{id}/reviews/movie")
	public int retrieveReviewsId(@PathVariable int id){
		
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		List<Reviews> rev=user.get().getReviews();
		int lastIndex = rev.size() - 1;
        if (lastIndex >= 0) {
            Reviews revs = rev.get(lastIndex);	
            return revs.getId();
        }
        return -1;
		
	}
	
	@CrossOrigin(origins="http://localhost:3000")
	@GetMapping("/jpa/users/{id}/reviews/{movie}")
	public Reviews retrieveReviewsByName(@PathVariable int id,@PathVariable String movie){
		
		Optional<User> user=repository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("id:"+id);
		}
		
		List<Reviews> rev=user.get().getReviews();
		for(Reviews r:rev) {
			System.out.println(movie);
			System.out.println(r.getMovieName());
			if(r.getMovieName().equals(movie)) {
				return r;
			}
		}
		Reviews rr=new Reviews();
		rr.setMovieName(movie);
        return rr;
		
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
	@GetMapping("/jpa/users/reviews/{id}/recommendation")
	public List<Recommendation> retrieveRecommendation(@PathVariable int id){
		Optional<Reviews> review=reviewsRepository.findById(id);
	
		
		return review.get().getRecommendations();
	}

	@CrossOrigin(origins="http://localhost:3000")
	@PostMapping("/jpa/users/reviews/{id}/recommendation")
	public String createRecommendation(@PathVariable int id,@RequestBody List<Recommendation> recommendations){
		Optional<Reviews> review=reviewsRepository.findById(id);
		

		
		for(Recommendation i:recommendations) {
			i.setReview(review.get());
			Recommendation savedRecommendation=recommendationRepository.save(i);
		}
		
		
		
		
		return "Success";
	}
	
	
	

	
	
}
package com.fdsoftware.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fdsoftware.domain.Post;
import com.fdsoftware.resources.util.URL;
import com.fdsoftware.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResorce {

	@Autowired
	private PostService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post post = service.findById(id);
		return ResponseEntity.ok().body(post);
	}
	
	//url para teste no postman: http://localhost:8080/posts/titlesearch?title=bom%20dia
	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "title", defaultValue = "") String title) {
		title = URL.decodeParam(title);
		List<Post> posts = service.findByTitle(title);
		return ResponseEntity.ok().body(posts);
	}
	
	@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());
		List<Post> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
	
	
}
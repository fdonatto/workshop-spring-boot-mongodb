package com.fdsoftware.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fdsoftware.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	

}

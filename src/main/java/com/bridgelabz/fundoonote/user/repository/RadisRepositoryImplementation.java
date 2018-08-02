package com.bridgelabz.fundoonote.user.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RadisRepositoryImplementation implements RedisRepository{

	 private static final String KEY = "TOKEN";
     
	    private RedisTemplate<String, String> redisTemplate;
	  
	    private HashOperations<String, String, String> hashOperations;
	  
	    @Autowired
	    public RadisRepositoryImplementation(RedisTemplate<String, String> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	    }
	  
	    @PostConstruct
	    private void init() {
	        hashOperations = redisTemplate.opsForHash();
	    }
	 
	    @Override
	    public void save(String token, String userId) {
	        hashOperations.put(KEY, token, userId);
	    }
	 
	    @Override
	    public String find(String token) {
	        return hashOperations.get(KEY, token);
	    }
}

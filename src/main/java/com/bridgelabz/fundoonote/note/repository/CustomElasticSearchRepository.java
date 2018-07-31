package com.bridgelabz.fundoonote.note.repository;



import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.fundoonote.note.model.Note;

public interface CustomElasticSearchRepository extends ElasticsearchRepository<Note, String>{
	
	List <Note> findAllByUserId(String userId);
}

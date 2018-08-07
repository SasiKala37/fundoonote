package com.bridgelabz.fundoonote.note.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoonote.note.model.Label;

@Repository
public interface LabelRepository extends MongoRepository<Label, String> {

	void save(Optional<Label> label);

	Optional<Label> findByLabelName(String labelname);

	Optional<Label> findByLabelNameAndUserId(String labelName, String userId);

	// List<Label> findAllByLabelName(String userId);

	List<Label> findByUserId(String userId);

}

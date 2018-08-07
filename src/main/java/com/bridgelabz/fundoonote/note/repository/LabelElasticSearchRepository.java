package com.bridgelabz.fundoonote.note.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.bridgelabz.fundoonote.note.model.Label;

public interface LabelElasticSearchRepository extends ElasticsearchRepository<Label, String> {

	List<Label> findByUserId(String userId);

	Optional<Label> findByLabelIdAndUserId(String labelId, String userId);

	Optional<Label> findByLabelNameAndUserId(String labelName, String userId);
}

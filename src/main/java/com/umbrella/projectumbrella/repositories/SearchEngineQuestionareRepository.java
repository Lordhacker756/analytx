package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.Punctuality;
import com.umbrella.projectumbrella.entities.SearchEngineQuestionare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchEngineQuestionareRepository extends JpaRepository<SearchEngineQuestionare, Integer> {
    SearchEngineQuestionare findSearchEngineQuestionnaireByInternName(String internName);
}

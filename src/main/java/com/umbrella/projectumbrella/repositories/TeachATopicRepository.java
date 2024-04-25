package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.SearchEngineQuestionare;
import com.umbrella.projectumbrella.entities.TeachATopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachATopicRepository extends JpaRepository<TeachATopic, Integer> {
    TeachATopic findTeachATopicByInternName(String internName);
}

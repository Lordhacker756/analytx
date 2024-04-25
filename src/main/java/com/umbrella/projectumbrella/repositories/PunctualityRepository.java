package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.PersonalityType;
import com.umbrella.projectumbrella.entities.Punctuality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunctualityRepository extends JpaRepository<Punctuality, Integer> {
    Punctuality findPunctualityByInternName(String internName);
}

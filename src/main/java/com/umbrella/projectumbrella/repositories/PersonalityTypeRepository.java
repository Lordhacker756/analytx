package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.PersonalDescription;
import com.umbrella.projectumbrella.entities.PersonalityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalityTypeRepository extends JpaRepository<PersonalityType, Integer> {
    PersonalityType findPersonalityTypeByInternName(String internName);
}

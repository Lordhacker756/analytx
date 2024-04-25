package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.Origami;
import com.umbrella.projectumbrella.entities.PersonalDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDescriptionRepository extends JpaRepository<PersonalDescription, Integer> {
    PersonalDescription findPersonalDescriptionByInternName(String internName);
}

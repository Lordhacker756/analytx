package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.Origami;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrigamiRepository extends JpaRepository<Origami, Integer> {
    public  Origami findOrigamiByInternName(String internName);
}

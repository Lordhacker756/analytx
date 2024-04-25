package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.TeachATopic;
import com.umbrella.projectumbrella.entities.TeamTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamTaskRepository extends JpaRepository<TeamTask, Integer> {
    TeamTask findTeamTaskByInternName(String internName);
}

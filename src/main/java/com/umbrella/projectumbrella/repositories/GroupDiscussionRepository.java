package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.GroupDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupDiscussionRepository extends JpaRepository<GroupDiscussion, Integer> {

    public GroupDiscussion findGroupDiscussionByInternName(String internName);

}

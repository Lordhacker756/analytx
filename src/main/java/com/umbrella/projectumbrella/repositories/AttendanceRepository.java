package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.AttendenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendenceEntity, Integer> {
    public AttendenceEntity findAttendenceByInternName(String internName);
}

package com.umbrella.projectumbrella.repositories;

import com.umbrella.projectumbrella.entities.GoogleSpreadsheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoogleSpreadsheetRepository extends JpaRepository<GoogleSpreadsheet, Integer> {
    public GoogleSpreadsheet findGoogleSpreadsheetByInternName(String internName);
}

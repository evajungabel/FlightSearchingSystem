package com.evajungabel.flightsearchsystem.repository;

import com.evajungabel.flightsearchsystem.domain.TravelDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelDocumentRepository extends JpaRepository<TravelDocument, Long> {
}

package com.evajungabel.flightsearchsystem.repository;

import com.evajungabel.flightsearchsystem.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
}

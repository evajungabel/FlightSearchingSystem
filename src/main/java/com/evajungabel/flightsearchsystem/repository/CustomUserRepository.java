package com.evajungabel.flightsearchsystem.repository;

import com.evajungabel.flightsearchsystem.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository  extends JpaRepository<CustomUser, Long> {

    CustomUser findByEmail(String email);

}

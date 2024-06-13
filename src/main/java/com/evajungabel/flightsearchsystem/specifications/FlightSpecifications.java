package com.evajungabel.flightsearchsystem.specifications;

import com.evajungabel.flightsearchsystem.domain.Flight;
import org.springframework.data.jpa.domain.Specification;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class FlightSpecifications {


    public static Specification<Flight> hasPlaceFrom(String placeFrom) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("placeFrom"), placeFrom);
    }

    public static Specification<Flight> hasPlaceTo(String placeTo) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("placeTo"), placeTo);
    }

    public static Specification<Flight> hasDepartureTimeGreaterThanOrEqualTo(LocalDate departureTime) {
        return (root, query, criteriaBuilder) -> {
            LocalDateTime startOfDay = departureTime.atStartOfDay();
            LocalDateTime endOfDay = departureTime.atTime(LocalTime.MAX);

            return criteriaBuilder.between(root.get("departureTime"), startOfDay, endOfDay);
        };
    }

//    public static Specification<Flight> hasArrivalTime(LocalDate arrivalTime) {
//        return (root, query, criteriaBuilder) ->
//                criteriaBuilder.equal(root.get("arrivalTime"), arrivalTime);
//    }

    public static Specification<Flight> hasNumberOfAvailableSeatsGreaterThanOrEqualTo(Integer numberOfPassengers) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("numberOfAvailableSeats"), numberOfPassengers);
    }


}


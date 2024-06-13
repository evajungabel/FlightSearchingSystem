package com.evajungabel.flightsearchsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlightFilterRequestInfo {

    private Long flightId;
    private String placeFrom;
    private String placeTo;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer duration;
    private Double price;
    private Integer numberOfPassengers;
    private Integer numberOfAvailableSeats;

}

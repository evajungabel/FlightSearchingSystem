package com.evajungabel.flightsearchsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlightFilterRequestForm {

    @NotBlank
    private String placeFrom;

    @NotBlank
    private String placeTo;

    private LocalDate departureTime;

    @NotBlank
    private Integer numberOfPassengers;
}

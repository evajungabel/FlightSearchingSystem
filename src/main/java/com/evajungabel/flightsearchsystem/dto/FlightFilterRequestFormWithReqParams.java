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
public class FlightFilterRequestFormWithReqParams {

    @NotBlank
    private String placeFrom;

    @NotBlank
    private String placeTo;

    private LocalDate departureTime;

    @NotBlank
    private Integer numberOfPassengers;

    private String sortDir;

    private String sort;

    private Integer page;

    private Integer size;
}

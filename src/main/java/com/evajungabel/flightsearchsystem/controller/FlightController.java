package com.evajungabel.flightsearchsystem.controller;

import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestForm;
import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestInfo;
import com.evajungabel.flightsearchsystem.service.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@Slf4j
public class FlightController {

    private FlightService flightService;

    @Autowired
    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping("/requests")
    @Operation(summary = "Getting filtered, paginated and sorted list of flights by anybody")
    @ApiResponse(responseCode = "200", description = "Paginated, sorted and filtered list of flights is got by anybody.")
    public ResponseEntity<List<FlightFilterRequestInfo>> findByRequestedValues(
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort,
            @RequestParam("page") String page,
            @RequestParam("size") String size,
            @RequestParam("placeFrom") String placeFrom,
            @RequestParam("placeTo") String placeTo,
            @RequestParam("departureTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureTime,
            @RequestParam("numberOfPassengers") String numberOfPassengers) {
        FlightFilterRequestForm flightFilterRequestForm = new FlightFilterRequestForm();
        flightFilterRequestForm.setPlaceFrom(placeFrom);
        flightFilterRequestForm.setPlaceTo(placeTo);
        flightFilterRequestForm.setDepartureTime(departureTime);
        flightFilterRequestForm.setNumberOfPassengers(Integer.valueOf(numberOfPassengers));

        log.info("Http request, GET /api/flights/requests, with variables: " + page + size + sort + sortDir);
        List<FlightFilterRequestInfo> flightFilterRequestInfos = flightService.getFlightRequests(Integer.parseInt(page), Integer.parseInt(size), sortDir, sort, flightFilterRequestForm);
        log.info("GET data from repository from /api/flights/requests, with variable: " + page + size + sort + sortDir);
        return new ResponseEntity<>(flightFilterRequestInfos, HttpStatus.OK);
    }

}

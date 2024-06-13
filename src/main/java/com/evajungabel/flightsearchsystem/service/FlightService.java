package com.evajungabel.flightsearchsystem.service;


import com.evajungabel.flightsearchsystem.domain.Flight;
import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestForm;
import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestInfo;
import com.evajungabel.flightsearchsystem.exceptions.FlightNotFoundException;
import com.evajungabel.flightsearchsystem.exceptions.NoResourceFoundException;
import com.evajungabel.flightsearchsystem.repository.FlightRepository;
import com.evajungabel.flightsearchsystem.specifications.FlightSpecifications;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class FlightService {


    private FlightRepository flightRepository;

    private ModelMapper modelMapper;

    @Autowired
    public FlightService(FlightRepository flightRepository, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.modelMapper = modelMapper;
    }


    public Flight findFlightById(Long flightId) {
        Optional<Flight> flightOptional = flightRepository.findById(flightId);
        if (flightOptional.isEmpty()) {
            throw new FlightNotFoundException(flightId);
        }
        return flightOptional.get();
    }

    public List<FlightFilterRequestInfo> getFlightRequests(int page, int size, String sortDir, String sort, FlightFilterRequestForm flightFilterRequestForm) {
        PageRequest pageReq
                = PageRequest.of(page, size, Sort.Direction.fromString(sortDir), sort);

        Specification<Flight> spec = Specification.where(null);

        spec = spec.and(FlightSpecifications.hasPlaceFrom(flightFilterRequestForm.getPlaceFrom()));

        spec = spec.and(FlightSpecifications.hasPlaceTo(flightFilterRequestForm.getPlaceTo()));

        spec = spec.and(FlightSpecifications.hasDepartureTimeGreaterThanOrEqualTo(flightFilterRequestForm.getDepartureTime()));

        spec = spec.and(FlightSpecifications.hasNumberOfAvailableSeatsGreaterThanOrEqualTo(flightFilterRequestForm.getNumberOfPassengers()));

        Page<Flight> matchingFlights = flightRepository.findAll(spec, pageReq);
        if (page > matchingFlights.getTotalPages()) {
            throw new NoResourceFoundException(matchingFlights.getTotalPages());
        }


        return matchingFlights.getContent().stream()
                .map(flight -> modelMapper.map(flight, FlightFilterRequestInfo.class))
                .collect(Collectors.toList());
    }

}

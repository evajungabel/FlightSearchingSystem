package com.evajungabel.flightsearchsystem.exceptions;

public class FlightNotFoundException extends RuntimeException{

    private final Long flightId;

    public FlightNotFoundException(Long flightId) {
        super("No flight found with id: " + flightId);
        this.flightId = flightId;
    }



    public Long getFlightId() {
        return flightId;
    }
}

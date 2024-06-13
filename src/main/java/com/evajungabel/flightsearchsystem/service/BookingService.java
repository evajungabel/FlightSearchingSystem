package com.evajungabel.flightsearchsystem.service;

import com.evajungabel.flightsearchsystem.domain.Booking;
import com.evajungabel.flightsearchsystem.domain.CustomUser;
import com.evajungabel.flightsearchsystem.domain.Flight;
import com.evajungabel.flightsearchsystem.domain.TravelDocument;
import com.evajungabel.flightsearchsystem.dto.BookingForm;
import com.evajungabel.flightsearchsystem.dto.BookingInfo;
import com.evajungabel.flightsearchsystem.repository.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookingService {

    private BookingRepository bookingRepository;
    private CustomUserService customUserService;
    private TravelDocumentService travelDocumentService;
    private FlightService flightService;
    private ModelMapper modelMapper;


    @Autowired
    public BookingService(BookingRepository bookingRepository, CustomUserService customUserService, TravelDocumentService travelDocumentService, FlightService flightService, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.customUserService = customUserService;
        this.travelDocumentService = travelDocumentService;
        this.flightService = flightService;
        this.modelMapper = modelMapper;
    }


    public BookingInfo saveBooking(BookingForm bookingForm) {
        if (customUserService.findByEmail(bookingForm.getEmail()) != null) {
            CustomUser user = customUserService.findCustomUserByEmail(bookingForm.getEmail());
            Booking booking = saveBookingWithUser(user, bookingForm);
            return modelMapper.map(booking, BookingInfo.class);
        } else {
            CustomUser user = modelMapper.map(bookingForm, CustomUser.class);
            customUserService.save(user);
            Booking booking = saveBookingWithUser(user, bookingForm);
            return modelMapper.map(booking, BookingInfo.class);
        }
    }

    public Booking saveBookingWithUser(CustomUser customUser, BookingForm bookingForm) {
        TravelDocument travelDocumentOfUser = modelMapper.map(bookingForm, TravelDocument.class);
        travelDocumentOfUser.setCustomUser(customUser);
        travelDocumentService.save(travelDocumentOfUser);

        customUser.setTravelDocuments(List.of(travelDocumentOfUser));
        Booking newBooking = new Booking();
        Flight flight = flightService.findFlightById(bookingForm.getFlightId());
        newBooking.setFlight(flight);
        newBooking.setCustomUser(customUser);
        return bookingRepository.save(newBooking);
    }

}

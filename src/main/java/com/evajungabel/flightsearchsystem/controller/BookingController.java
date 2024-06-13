package com.evajungabel.flightsearchsystem.controller;

import com.evajungabel.flightsearchsystem.dto.BookingForm;
import com.evajungabel.flightsearchsystem.dto.BookingInfo;
import com.evajungabel.flightsearchsystem.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/save-booking")
@Slf4j
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    @Operation(summary = "Saving booking")
    @ApiResponse(responseCode = "201", description = "Booking is saved by costumuser")
    public ResponseEntity<BookingInfo> saveBooking(@Valid @RequestBody BookingForm bookingForm) {
        log.info("Http request, POST /api/save-booking, body: " + bookingForm.toString());
        BookingInfo bookingInfo = bookingService.saveBooking(bookingForm);
        log.info("POST data from repository from /api/save-booking, body: " + bookingForm);
        return new ResponseEntity<>(bookingInfo, HttpStatus.CREATED);
    }
}

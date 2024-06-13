package com.evajungabel.flightsearchsystem.controller;


import com.evajungabel.flightsearchsystem.domain.Flight;
import com.evajungabel.flightsearchsystem.dto.CustomUserForm;
import com.evajungabel.flightsearchsystem.dto.CustomUserInfo;
import com.evajungabel.flightsearchsystem.dto.CustomUserListOfBookingsForm;
import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestInfo;
import com.evajungabel.flightsearchsystem.service.CustomUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customusers")
@Slf4j
public class CustomUserController {

    private CustomUserService customUserService;
    @Autowired
    public CustomUserController(CustomUserService customUserService) {
        this.customUserService = customUserService;
    }


    @GetMapping("/bookings")
    @Operation(summary = "Getting booking with email by customuser")
    @ApiResponse(responseCode = "200", description = "Get booking with email by customuser")
//    @SecurityRequirement(name = "basicAuth")
//    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<List<FlightFilterRequestInfo>> getFlightsOfBookings(
            @RequestParam("sortDir") String sortDir,
            @RequestParam("sort") String sort,
            @RequestParam("page") String page,
            @RequestParam("size") String size,
            @RequestParam("email") String email) {
        CustomUserListOfBookingsForm customUserListOfBookingsForm = new CustomUserListOfBookingsForm();
        customUserListOfBookingsForm.setEmail(email);
        customUserListOfBookingsForm.setSize(Integer.parseInt(size));
        customUserListOfBookingsForm.setPage(Integer.parseInt(page));
        customUserListOfBookingsForm.setSortDir(sortDir);
        customUserListOfBookingsForm.setSort(sort);

//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("Http request, GET /api/customusers/bookings, with username: " + userDetails.getUsername());
        log.info("Http request, GET /api/customusers/bookings, with username: ");
        List<FlightFilterRequestInfo> flightFilterRequestInfos = customUserService.getBookings(customUserListOfBookingsForm);
//        List<FlightFilterRequestInfo> flightFilterRequestInfos = customUserService.getBookings(customUserListOfBookingsForm, userDetails.getUsername());
        log.info("GET data from repository from /api/customusers/bookings, with username: ");
//        log.info("GET data from repository from /api/customusers/bookings, with username: " + userDetails.getUsername());
        return new ResponseEntity<>(flightFilterRequestInfos, HttpStatus.OK);
    }

}



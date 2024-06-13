package com.evajungabel.flightsearchsystem.service;

import com.evajungabel.flightsearchsystem.domain.Booking;
import com.evajungabel.flightsearchsystem.domain.CustomUser;
import com.evajungabel.flightsearchsystem.domain.Flight;
import com.evajungabel.flightsearchsystem.dto.CustomUserListOfBookingsForm;
import com.evajungabel.flightsearchsystem.dto.FlightFilterRequestInfo;
import com.evajungabel.flightsearchsystem.exceptions.EmailAddressNotFoundException;
import com.evajungabel.flightsearchsystem.repository.CustomUserRepository;
import org.hibernate.validator.cfg.defs.CurrencyDef;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomUserService  implements UserDetailsService {

    private CustomUserRepository customUserRepository;

    private ModelMapper modelMapper;
    @Autowired
    public CustomUserService(CustomUserRepository customUserRepository, ModelMapper modelMapper) {
        this.customUserRepository = customUserRepository;
        this.modelMapper = modelMapper;
    }

    public void save(CustomUser customUser) {
        customUserRepository.save(customUser);
    }

    public List<FlightFilterRequestInfo> getBookings(CustomUserListOfBookingsForm customUserListOfBookingsForm) {
        CustomUser customUser = findCustomUserByEmail(customUserListOfBookingsForm.getEmail());
        //TODO username equals with username of customuser
        List<Booking> bookings = customUser.getBookings();
        List<FlightFilterRequestInfo> flightsOfBookings = new ArrayList<>();
        for (int i = 0; i < bookings.size(); i++) {
            flightsOfBookings.add(modelMapper.map(bookings.get(i).getFlight(), FlightFilterRequestInfo.class));
        }
        System.out.println(flightsOfBookings);
        return flightsOfBookings;
    }

    public CustomUser findCustomUserByEmail(String email) {
        Optional<CustomUser> customUserOptional = Optional.ofNullable(customUserRepository.findByEmail(email));
        if (customUserOptional.isEmpty()) {
            throw new EmailAddressNotFoundException(email);
        }
        return customUserOptional.get();
    }

    public CustomUser findByEmail(String email) {
        return customUserRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }



}

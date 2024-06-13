package com.evajungabel.flightsearchsystem.dto;

import com.evajungabel.flightsearchsystem.config.CustomUserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomUserInfo {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;

}

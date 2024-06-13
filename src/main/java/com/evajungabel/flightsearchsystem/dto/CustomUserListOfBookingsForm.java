package com.evajungabel.flightsearchsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CustomUserListOfBookingsForm {

    private String sortDir;

    private String sort;

    private Integer page;

    private Integer size;

    @NotNull(message = "E-mail cannot be empty!")
    @Size(min = 8, max = 200, message = "Min 8 or max 100 characters can be!")
    @Email
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Must be right!")
    private String email;

}

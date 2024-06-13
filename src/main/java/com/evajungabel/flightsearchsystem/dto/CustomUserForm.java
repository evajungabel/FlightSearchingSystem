package com.evajungabel.flightsearchsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomUserForm {

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, max = 200, message = "Min 3 or max 200 characters can be!")
    private String firstName;

    @NotBlank(message = "Name cannot be empty!")
    @Size(min = 3, max = 200, message = "Min 3 or max 200 characters can be!")
    private String lastName;

    @NotBlank(message = "Username cannot be empty!")
    @Size(min = 3, max = 200, message = "Min 3 or max 200 characters can be!")
    private String username;

    @NotNull(message = "Password cannot be empty!")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
            message = "Must have lowercase, uppercase letter, number, sepcial sign, and min 8 characters!")
    private String password;

    @NotNull(message = "Phone number cannot be empty!")
    @Size(min = 1, max = 200, message = "Min 5 and max 20 numbers can be starting with + sign!")
    @Pattern(regexp = "^(\\+{0,})(\\d{0,})([(]{1}\\d{1,3}[)]{0,}){0,}(\\s?\\d+|\\+\\d{2,3}\\s{1}\\d+|\\d+){1}[\\s|-]?\\d+([\\s|-]?\\d+){1,2}(\\s){0,}$",
            message = "Must be right!")
    private String phoneNumber;


    @NotNull(message = "E-mail cannot be empty!")
    @Size(min = 8, max = 200, message = "Min 8 or max 100 characters can be!")
    @Email
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Must be right!")
    private String email;

//    @NotNull(message = "ÁSZ and ASZ cannot be empty!")
    private Boolean ASZFAndASZ;

}

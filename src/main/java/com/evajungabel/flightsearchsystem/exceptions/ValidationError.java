package com.evajungabel.flightsearchsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {
    private List<ApiError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        fieldErrors.add(new ApiError(field, "Validation Error", message));
    }

    public List<ApiError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<ApiError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
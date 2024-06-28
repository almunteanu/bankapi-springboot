package dev.almuntex.bankapi.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ValidationErrorDto {

    private String message;
    @JsonProperty("invalid_fields")
    private List<String> invalidFields;

    public ValidationErrorDto(String message, List<String> invalidFields) {
        this.message = message;
        this.invalidFields = invalidFields;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<String> invalidFields) {
        this.invalidFields = invalidFields;
    }
}

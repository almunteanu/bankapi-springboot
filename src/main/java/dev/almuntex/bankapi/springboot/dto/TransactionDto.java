package dev.almuntex.bankapi.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class TransactionDto {

    @NotBlank
    @JsonProperty("receiving_user_id")
    private String receivingUserId;

    @DecimalMin("0.01")
    @DecimalMax("99.99")
    @Digits(integer = 2, fraction = 2)
    private BigDecimal amount;

    @NotBlank
    private String reference;

    public String getReceivingUserId() {
        return receivingUserId;
    }

    public void setReceivingUserId(String receivingUserId) {
        this.receivingUserId = receivingUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

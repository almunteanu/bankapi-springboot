package dev.almuntex.bankapi.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Table("transactions")
public class Transaction {

    @Id
    private String id;
    @JsonProperty("user_id")
    private String userId;
    private BigDecimal amount;
    private String reference;
    @JsonProperty("date_created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mmZ")
    private OffsetDateTime dateCreated;
    @JsonProperty("bank_slogan")
    private String bankSlogan;

    public Transaction() {
    }

    public Transaction(String userId,
                       BigDecimal amount,
                       String reference,
                       OffsetDateTime dateCreated,
                       String bankSlogan) {
        this.userId = userId;
        this.amount = amount;
        this.reference = reference;
        this.dateCreated = dateCreated;
        this.bankSlogan = bankSlogan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getBankSlogan() {
        return bankSlogan;
    }

    public void setBankSlogan(String bankSlogan) {
        this.bankSlogan = bankSlogan;
    }
}

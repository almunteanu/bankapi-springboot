package dev.almuntex.bankapi.springboot.service;

import dev.almuntex.bankapi.springboot.model.Transaction;
import dev.almuntex.bankapi.springboot.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Component
public class TransactionService {

    private final String bankSlogan;
    private final TransactionRepository transactionRepository;

    public TransactionService(@Value("${bank.slogan}") String bankSlogan,
                              TransactionRepository transactionRepository) {
        this.bankSlogan = bankSlogan;
        this.transactionRepository = transactionRepository;
    }

    public Iterable<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Iterable<Transaction> findByUserId(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    public Transaction create(String userId, BigDecimal amount, String reference, OffsetDateTime dateCreated) {
        Transaction transaction = new Transaction(userId, amount, reference, dateCreated, bankSlogan);
        return transactionRepository.save(transaction);
    }
}

package dev.almuntex.bankapi.springboot.web;

import dev.almuntex.bankapi.springboot.dto.TransactionDto;
import dev.almuntex.bankapi.springboot.model.Transaction;
import dev.almuntex.bankapi.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public Iterable<Transaction> findAll() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getReceivingUserId(), transactionDto.getAmount(),
                transactionDto.getReference(), OffsetDateTime.now(ZoneOffset.of("+03:00")));
    }

    @GetMapping("/account/{userId}")
    public Iterable<Transaction> findTransactionsByUserId(@PathVariable(name = "userId") String userId) {
        return transactionService.findByUserId(userId);
    }
}

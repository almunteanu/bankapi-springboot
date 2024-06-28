package dev.almuntex.bankapi.springboot.web;

import dev.almuntex.bankapi.springboot.dto.TransactionDto;
import dev.almuntex.bankapi.springboot.model.Transaction;
import dev.almuntex.bankapi.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody @Valid TransactionDto transactionDto) {
        return transactionService.create(transactionDto.getReceivingUserId(), transactionDto.getAmount(),
                transactionDto.getReference(), OffsetDateTime.now(ZoneOffset.of("+03:00")));
    }
}

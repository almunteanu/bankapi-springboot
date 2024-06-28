package dev.almuntex.bankapi.springboot.repository;

import dev.almuntex.bankapi.springboot.model.Transaction;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends CrudRepository<Transaction, String> {

    @Query("SELECT * FROM \"transactions\" WHERE user_id = :userId")
    Iterable<Transaction> findByUserId(@Param("userId") String userId);
}

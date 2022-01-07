package net.lumae.api.repository;

import dev.samkist.lumae.sagittarius.data.models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public List<Transaction> findByEmail(String email);
}


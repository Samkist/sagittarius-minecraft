package me.cbotte21.LumaeMC.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public List<Transaction> findByEmail(String email);
}


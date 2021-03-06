package net.lumae.api.controllers.global;

import dev.samkist.lumae.sagittarius.data.models.Transaction;
import net.lumae.api.repository.RecordNotFoundException;
import net.lumae.api.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    @Autowired
    private final TransactionRepository repository;

    public final String tag = "transactions";

    public TransactionController(TransactionRepository repository) {
        this.repository = repository;
    }

    //Get all
    @GetMapping("/transactions")
    public List<Transaction> all() {
        return repository.findAll();
    }

    //Create new
    @PostMapping("/transactions")
    public Transaction newTransaction(@RequestBody Transaction transaction) {
        return repository.save(transaction);
    }

    @GetMapping("/transactions/{id}")
    public Transaction one(@PathVariable String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id, tag));
    }
}

package com.mindhub.Homebanking.services.implement;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;
import com.mindhub.Homebanking.repositories.TransactionRepository;
import com.mindhub.Homebanking.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public TransactionDTO getTransactions(long id) {
        return transactionRepository.findById(id).map(TransactionDTO::new).orElse(null);
    }

    @Override
    public void saveTransaction(Transaction transaction) { transactionRepository.save(transaction);}
}

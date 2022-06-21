package com.mindhub.Homebanking.services;

import com.mindhub.Homebanking.dtos.TransactionDTO;
import com.mindhub.Homebanking.models.Transaction;

public interface TransactionService {
    TransactionDTO getTransactions(long id);
    void saveTransaction(Transaction transaction);

}

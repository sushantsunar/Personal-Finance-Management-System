package com.ss.pfms.service;

import com.ss.pfms.entity.Transaction;
import com.ss.pfms.entity.User;
import java.util.List;

public interface TransactionService {
    Transaction addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions(User user);
}
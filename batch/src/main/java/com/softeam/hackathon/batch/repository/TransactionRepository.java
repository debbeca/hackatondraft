package com.softeam.hackathon.batch.repository;

import com.softeam.hackathon.batch.data.Transaction;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by hackathon6 on 15/06/2017.
 */
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
}

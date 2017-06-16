package com.softeam.hackathon.batch.listener;

import com.softeam.hackathon.batch.repository.TransactionRepository;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class CountListener implements StepExecutionListener {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("---------------------------------");
        System.out.println("TRANSACTION IN DB BEFORE = " + transactionRepository.count());
        System.out.println("---------------------------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("---------------------------------");
        System.out.println("TRANSACTION IN DB AFTER = " + transactionRepository.count());
        System.out.println("---------------------------------");

        return stepExecution.getExitStatus();
    }
}

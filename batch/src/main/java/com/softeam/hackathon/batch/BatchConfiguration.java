package com.softeam.hackathon.batch;

import com.softeam.hackathon.batch.data.Transaction;
import com.softeam.hackathon.batch.listener.CountListener;
import com.softeam.hackathon.batch.processor.NLUProcessor;
import com.softeam.hackathon.batch.reader.MailGeneratorReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Bean
    public MailGeneratorReader mailGeneratorReader() {
        return new MailGeneratorReader();
    }

    @Bean
    public NLUProcessor nluProcessor() {
        return new NLUProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Transaction> transactionWriter() {
        JdbcBatchItemWriter<Transaction> writer = new JdbcBatchItemWriter<Transaction>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Transaction>());
        writer.setSql("Insert into TRANSACTION (BUYER, CURRENCY, FEES, PRODUCT, QUANTITY, SELLER, TOTAL_AMOUNT, TRADE_DATE, UNIT_PRICE)" +
                " values (:buyer, :currency, :fees, :product, :quantity, :seller, :totalAmount, :tradeDate, :unitPrice)");

        writer.setDataSource(dataSource);
        return writer;
    }

    @Bean
    public CountListener countListener() {
        return new CountListener();
    }

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepImportMail())
                .end()
                .build();
    }

    @Bean
    public Step stepImportMail() {
        return stepBuilderFactory.get("stepImportMail")
                .<String, Transaction> chunk(2)
                .reader(mailGeneratorReader())
                .processor(nluProcessor())
                .writer(transactionWriter())
                .listener(countListener())
                .build();
    }
}

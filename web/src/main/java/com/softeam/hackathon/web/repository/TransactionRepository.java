package com.softeam.hackathon.web.repository;

import com.softeam.hackathon.web.data.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "transaction", path = "transaction")
public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {

    // tendancy

    @Query(value = "select t.trade_date, sum(t.total_amount)" +
            "  from Transaction t" +
            " where t.trade_date >= :dateFrom and t.trade_date <= :dateTo" +
            " group by t.trade_date" +
            " order by t.trade_date", nativeQuery = true)
    List<Object[]> selectTendancy(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

    // BEST

    @Query(value = "select t.buyer, sum(t.total_amount) as sum_total_amount" +
            "  from Transaction t" +
            " where t.trade_date >= :dateFrom and t.trade_date <= :dateTo" +
            " group by t.buyer" +
            " order by sum_total_amount desc, t.buyer", nativeQuery = true)
    List<Object[]> selectBestBuyer(@Param("dateFrom") Date dateFrom, @Param("dateTo") Date dateTo);

}

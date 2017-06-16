package com.softeam.hackathon.web.service;

import com.softeam.hackathon.web.model.Buyer;
import com.softeam.hackathon.web.model.Intervalle;
import com.softeam.hackathon.web.model.Tendancy;
import com.softeam.hackathon.web.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TendanceService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Tendancy> researchTendancy(Intervalle intervalle) {
        LocalDate localDateFrom;
        LocalDate localDateTo = LocalDate.now();

        switch (intervalle) {
            case MOIS:
                localDateFrom = localDateTo.minusMonths(1);
                break;
            case SEMAINE:
                localDateFrom = localDateTo.minusWeeks(1);
                break;
            default:
                localDateFrom = localDateTo.minusDays(1);
        }

        Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        return mapTendancy(transactionRepository.selectTendancy(dateFrom, dateTo));
    }

    private List<Tendancy> mapTendancy(List<Object[]> objects) {
        List<Tendancy> tendancyList = new ArrayList<>();
        for (Object[] currentArray : objects) {
            tendancyList.add(new Tendancy(currentArray[0], currentArray[1]));
        }

        return tendancyList;
    }


    public List<Buyer> researchBuyer(Intervalle intervalle) {
        LocalDate localDateFrom;
        LocalDate localDateTo = LocalDate.now();

        switch (intervalle) {
            case MOIS:
                localDateFrom = localDateTo.minusMonths(1);
                break;
            default:
                localDateFrom = localDateTo.minusWeeks(1);
        }

        Date dateFrom = Date.from(localDateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateTo = Date.from(localDateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Buyer> buyerList =  mapBuyer(transactionRepository.selectBestBuyer(dateFrom, dateTo));

        if (buyerList.size() > 5) {
            return buyerList.subList(0, 5);
        } else {
            return buyerList;
        }
    }

    private List<Buyer> mapBuyer(List<Object[]> objects) {
        List<Buyer> buyerList = new ArrayList<>();
        for (Object[] currentArray : objects) {
            buyerList.add(new Buyer(currentArray[0], currentArray[1]));
        }

        return buyerList;
    }


}

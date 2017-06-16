package com.softeam.hackathon.web.controller;

import com.softeam.hackathon.web.model.Buyer;
import com.softeam.hackathon.web.model.Conseil;
import com.softeam.hackathon.web.model.Intervalle;
import com.softeam.hackathon.web.model.Tendancy;
import com.softeam.hackathon.web.service.TendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private TendanceService tendanceService;

    @RequestMapping(value="tendancy", method = RequestMethod.GET)
    public List<Tendancy> getTendancy(@RequestParam("intervalle") Optional<String> intervalle) {
        List<Tendancy> tendancyList;

        if (intervalle.isPresent()) {
            tendancyList = tendanceService.researchTendancy(Intervalle.fromString(intervalle.get()));
        } else {
            tendancyList = tendanceService.researchTendancy(Intervalle.JOUR);
        }

        return tendancyList;
    }

    @RequestMapping(value="bestBuyer", method = RequestMethod.GET)
    public List<Buyer> getBestBuyer(@RequestParam("intervalle") Optional<String> intervalle) {
        List<Buyer> buyerList;

        if (intervalle.isPresent()) {
            buyerList = tendanceService.researchBuyer(Intervalle.fromString(intervalle.get()));
        } else {
            buyerList = tendanceService.researchBuyer(Intervalle.SEMAINE);
        }

        return buyerList;
    }

    @RequestMapping(value="conseil", method = RequestMethod.GET)
    public Conseil getBestBuyer() {
        Conseil conseil = new Conseil();

        conseil.setBuy("PRODUCT_TO_BUY");
        conseil.setSell("PRODUCT_TO_SELL");
        conseil.setKeep("PRODUCT_TO_KEEP");

        return conseil;
    }

}

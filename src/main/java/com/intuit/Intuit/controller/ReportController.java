package com.intuit.Intuit.controller;

import com.intuit.Intuit.ReaderService;
import com.intuit.Intuit.dao.StatisticsRepository;
import com.intuit.Intuit.model.StatisticReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reports")
public class ReportController {

    @Autowired
    StatisticsRepository statisticsRepository;

    @GetMapping(produces = "application/json")
    public StatisticReport findReport(){
        return statisticsRepository.findFirstByOrderByIdAsc();
    }
}

package com.intuit.Intuit.dao;

import com.intuit.Intuit.model.StatisticReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsRepository extends CrudRepository<StatisticReport, Long> {

    StatisticReport findFirstByOrderByIdAsc();

    StatisticReport save(StatisticReport statisticReport);
}

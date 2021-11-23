package com.intuit.Intuit.model;

import javax.persistence.*;

@Entity
@Table(name = "statistics")
public class StatisticReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer firstNameNullCounts;
    private Integer lastNameNullCounts;
    private Integer ageNullCounts;

    private double averageAge;

    public StatisticReport(){

    }

    public StatisticReport(Integer firstNameNullCounts, Integer lastNameNullCounts, Integer ageNullCounts, double averageAge) {
        this.firstNameNullCounts = firstNameNullCounts;
        this.lastNameNullCounts = lastNameNullCounts;
        this.ageNullCounts = ageNullCounts;
        this.averageAge = averageAge;
    }

    public Integer getFirstNameNullCounts() {
        return firstNameNullCounts;
    }

    public void setFirstNameNullCounts(Integer firstNameNullCounts) {
        this.firstNameNullCounts = firstNameNullCounts;
    }

    public Integer getLastNameNullCounts() {
        return lastNameNullCounts;
    }

    public void setLastNameNullCounts(Integer lastNameNullCounts) {
        this.lastNameNullCounts = lastNameNullCounts;
    }

    public Integer getAgeNullCounts() {
        return ageNullCounts;
    }

    public void setAgeNullCounts(Integer ageNullCounts) {
        this.ageNullCounts = ageNullCounts;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

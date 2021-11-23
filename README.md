# Intuit-Home-Assignment

#### Java 8
#### Spring Boot 2.6.0.RELEASE
#### Spring data
#### H2 DB
#### Junit test 4

### Instructios:

1. Use application.properties and set your csv location in file.path param
2. Run program.

### Testing:
1. I used my default csv files that i created, you can create your own and set and change the path in each test.

### Assumptions:
1. Null value in csv files = empty cell.
2. Path of csv url should be correct.
3. There is only 1 time that we save the report statistics inside the db, so there be only 1 item.

### Answers to questions:
**Q:** How will you change your implementation if you need to ingest a daily csv and return a
report per Day ?

**A**: A new field be added at StatisticReport Entity "timestamp". In addition new api post request will added for saveReportStatistics,  and for every day that the user will send the report through the api request, calculation of statistics will done, and then we will save the statistics in our db with the today date. when user will ask the statistic of report, we will return back the information using new query with "WHERE" caluse and search for today's date statistic.

**Q**: How will you change your implementation if you need to ingest a daily csv and return
statistics over a period of time (week,month,year,custom dates) ?

**A**: New endpoint will added inside StatisticsRepository getStatisticBetweenPeriods with dates paramters. Then we will create custom query and get the all statistic from those dates. after that we can calculate the all nulls just buy sum values, and for average we will sum the total average per period and divide by total days for new average.



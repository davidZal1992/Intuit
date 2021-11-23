package com.intuit.Intuit;
import com.intuit.Intuit.dao.StatisticsRepository;
import com.intuit.Intuit.model.Person;
import com.intuit.Intuit.model.StatisticReport;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderService {

    private StatisticsRepository statisticsRepository;
    private List<Person> personDetailsList;

    public ReaderService(@Value("${file.path}") String csv, @Autowired StatisticsRepository statisticsRepository) throws IOException {
        if(!csv.equals("")) {
            this.statisticsRepository = statisticsRepository;
            personDetailsList = new CsvToBeanBuilder<Person>(new FileReader(csv))
                    .withType(Person.class).build().parse();
            StatisticReport statisticReport = calculateStatistics();
            this.statisticsRepository.save(statisticReport);
        }
    }

    private StatisticReport calculateStatistics() {

        Integer firstNameNullCounter = 0;
        Integer lastNameNullCounter = 0;
        Integer ageNullCounter= 0;

       for(Person person : personDetailsList){
           if(person.getFirstName().equals("") || person.getFirstName() == null) {
               firstNameNullCounter++;
           }
           if(person.getLastName().equals("") || person.getLastName() == null) {
               lastNameNullCounter++;
           }
           if(person.getAge() == null){
               ageNullCounter++;
           }
        }

       return new StatisticReport(firstNameNullCounter,lastNameNullCounter,ageNullCounter,calculateAverage());
    }


    private double calculateAverage(){
        return personDetailsList.stream()
                .map(p -> p.getAge() == null ? new Person(p.getFirstName(),p.getLastName(),0) : p)
                .collect(Collectors.averagingDouble(Person::getAge));
    }



}

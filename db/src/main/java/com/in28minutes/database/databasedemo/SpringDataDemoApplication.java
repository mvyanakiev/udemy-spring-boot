package com.in28minutes.database.databasedemo;


import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.springdata.PersonSpringDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class SpringDataDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonSpringDataRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        logger.info("All users -> {}", repository.findAll());

        Person person = new Person( "Tara", "Sofia", new Date());
        repository.save(person);

        logger.info("User id = 1 is {}", repository.findById(1));

        logger.info("Location is Amsterdam {}", repository.findPersonByLocation("Amsterdam"));

        person.setName("John");
        repository.save(person);
        logger.info("User id = 1 is {}", repository.findById(1));
    }
}

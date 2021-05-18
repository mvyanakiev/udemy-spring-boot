package com.in28minutes.database.databasedemo;


import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJbdcDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Date;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PersonJbdcDao dao;

    public static void main(String[] args) {
        SpringApplication.run(DatabaseDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("All users -> {}", dao.findAll());
        logger.info("{} row(s) deleted.", dao.deleteById(10004));
        Person person = new Person(125, "Tara", "Sofia", new Date());
        dao.insert(person);
        logger.info("Users from Amsterdam is: {}", dao.findByLocation("Amsterdam"));
        logger.info("User id = 125 is {}", dao.findById(125));
        person.setName("John");
        dao.update(person);
        logger.info("User id = 125 is {}", dao.findById(125));
        logger.info("User John: {}", dao.findByName("John"));
    }
}

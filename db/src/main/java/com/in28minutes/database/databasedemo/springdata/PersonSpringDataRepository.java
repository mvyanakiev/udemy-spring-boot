package com.in28minutes.database.databasedemo.springdata;

import com.in28minutes.database.databasedemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonSpringDataRepository extends JpaRepository<Person, Integer> {

    public List<Person> findPersonByLocation(String location);
}

package dev.jigar.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
// this annoation is typically used in DATA ACCESS LAYER of an app, where classes r responsible for interacting with DATABASE to perfrom CRUD operations.
public class RunRepository {
    
    private List<Run> runs = new ArrayList<>();

    List<Run> findAll() {
        return runs;
    }

    @PostConstruct 
    // this annoation says whenever this class' obj is created, automatically call init(), it is used for initialization
    private void init() {
        runs.add(new Run(1, "morning run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.INDOOR));
        runs.add(new Run(2, "evening run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.OUTDOOR));

    }
}

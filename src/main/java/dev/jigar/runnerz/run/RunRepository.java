package dev.jigar.runnerz.run;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Repository
// this annoation is typically used in DATA ACCESS LAYER of an app, where classes r responsible for interacting with DATABASE to perfrom CRUD operations.
public class RunRepository {
    
    private List<Run> runs = new ArrayList<>();

    // List<Run> findAll() {
    //     return runs;
    // }

    // Optional<Run> findById(Integer id) {
    //     return runs.stream().filter(run -> run.id() == id).findFirst();
    // }

    // // post
    // void create(Run run) {
    //     runs.add(run);  
    // }

    // // update
    // void update(Run run, Integer id) {
    //     Optional<Run> existingRun = findById(id);
    //     if (existingRun.isPresent()){
    //         runs.set(runs.indexOf(existingRun.get()), run);
    //     }
    // }

    // void delete(Integer id) {
    //     runs.removeIf(run -> run.id().equals(id));
    // }

    // @PostConstruct 
    // // this annoation says whenever this class' obj is created, automatically call init(), it is used for initialization
    // private void init() {
    //     runs.add(new Run(1, "morning run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.INDOOR));
    //     runs.add(new Run(2, "evening run", LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS), 1, Location.OUTDOOR));

    // }

    public static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    public final JdbcClient jdbcClient;

    public RunRepository(JdbcClient _jdbcClient) {
        this.jdbcClient = _jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
        // .query EXECUTES the query
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("select * from run where id = ?")
        .param(id)
        .query(Run.class)
        .optional();
    }

    // post
    public void create(Run run) {
        // String locationString = (run.location() == Location.INDOOR) ? "INDOOR" : "OUTDOOR";
        var updated = jdbcClient.sql("insert into run (id, title, started_on, completed_on, miles, location) values (?,?,?,?,?,?)")
        .params(List.of(run.id(), run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().name()))        
        .update();

        // .update returns the no. of affected rows

        Assert.state(updated == 1, "Failed to create run" + run.title());
    }

    // update
    public void update(Run run, Integer id) {
        // `.name()` param converts the location object to string
        // can also use `.toString()`
        var updated = jdbcClient.sql("update run set title = ?, started_on = ?, completed_on = ?, miles = ?, location = ? where id = ?")
        .params(List.of(run.title(), run.startedOn(), run.completedOn(), run.miles(), run.location().name(), run.id()))
        .update();

        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    // delete
    public void delete(Integer _id) {
        var updated = jdbcClient.sql("delete from run where id = ?")
        .param(_id)
        .update();

        Assert.state(updated == 1, "Failed to delete run of id = " + _id);
    }

    public int count() {
        return jdbcClient.sql("select * from run")
        .query()
        .listOfRows()
        .size();
    }

    public void saveAll(List<Run> runs) {
        // Iterates over each Run object in the provided list and calls the create method for each one.
        runs.stream().forEach(this::create);
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("select * from run where location = :location")
        .param(location)
        .query(Run.class)
        .list();
    }
}

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

// this annoation is typically used in DATA ACCESS LAYER of an app, where classes r responsible for interacting with DATABASE to perfrom CRUD operations.
@Repository
public class JdbcClientRunRepository {

    public static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    public final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient _jdbcClient) {
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
package dev.jigar.runnerz.run;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunRepository extends ListCrudRepository<Run, Integer> {
    List<Run> findAllByLocation(Location location);
    // List<Run> findByMiles(Integer miles);
}

package dev.jigar.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/runs")
public class RunController {
    
    private final RunRepository runRepository;

    public RunController(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @GetMapping("/")
    List<Run> findAll() {
        return runRepository.findAll();
    }   

    // // get
    @GetMapping("/{id}")
    Run findId(@PathVariable Integer id) {
        Optional<Run> run = runRepository.findById(id);
        if (run.isEmpty()) {
            // throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OOPS!! RUN NOT FOUND");
            throw new RunNotFoundException();
        }
        return run.get();
    }

    // post
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    void create(@Valid @RequestBody Run run) {
        // runRepository.create(run);  
        runRepository.save(run);
    }
    
    // update
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}/")
    void update(@Valid @RequestBody Run run, @PathVariable Integer id) {
        // runRepository.update(run, id);
        runRepository.save(run);
        // runRepository.update(run, id);
        runRepository.save(run);
    }
    
    // delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}/")
    void delete(@PathVariable Integer id) {
        // runRepository.delete(id);
        runRepository.deleteById(id);
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location) {
        return runRepository.findAllByLocation(location);
    }
    
}

package dev.jigar.runnerz.run;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JdbcClientRunRepository runRepository;

    // using this to map n deserialize the data from json file into runs
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(JdbcClientRunRepository _runRepository, ObjectMapper _objectMapper) {
        this.runRepository = _runRepository;
        this.objectMapper = _objectMapper;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if (runRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) { 
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from the database", allRuns.runs().size());
                runRepository.saveAll(allRuns.runs());
            } catch (IOException e) {   
                throw new RuntimeException("Failed to read json data", e);
            }
        } else {
            log.info("Not loading runs from json data.");
        }
    }
}

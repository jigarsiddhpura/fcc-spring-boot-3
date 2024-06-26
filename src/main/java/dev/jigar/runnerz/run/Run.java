package dev.jigar.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
    Integer id,
    @NotEmpty
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    @Positive
    Integer miles,
    Location location
) {
    // spl type of class to model immutable data
    // provides implementation methods - toString, hashCode, equals
    // automatically generate constructors, getters

    public Run {
        if (!completedOn.isAfter(startedOn)) {
            throw new IllegalArgumentException("CompletedOn must be after Started on");
        }
    }
}

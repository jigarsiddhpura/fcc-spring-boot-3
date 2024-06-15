package dev.jigar.runnerz;

import java.time.LocalDateTime;

public record Run(
    Integer id,
    String title,
    LocalDateTime startedOn,
    LocalDateTime completedOn,
    Integer miles,
    Location location
) {
    // spl type of class to model immutable data
    // provides implementation methods - toString, hashCode, equals
    // automatically generate constructors, getters
}

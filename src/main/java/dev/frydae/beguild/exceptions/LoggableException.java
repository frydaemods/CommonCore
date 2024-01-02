package dev.frydae.beguild.exceptions;

import lombok.Getter;
import org.slf4j.event.Level;

@Getter
public class LoggableException extends Exception {
    private final Level level;

    public LoggableException(String message) {
        this(message, Level.ERROR);
    }

    public LoggableException(String message, Level level) {
        super(message);

        this.level = level;
    }
}

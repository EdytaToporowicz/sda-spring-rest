package pl.blogservice;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimestampProvider {

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now();
    }
}

package hu.cj.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FreeDate {
    private LocalDateTime start;
    private LocalDateTime end;

    public FreeDate(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}

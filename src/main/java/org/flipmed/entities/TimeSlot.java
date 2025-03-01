package org.flipmed.entities;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class TimeSlot {
    private LocalTime startTime;
    private LocalTime endTime;

    public TimeSlot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    // Parse string like "9:30-10:00"
    public static TimeSlot parse(String slotString) throws Exception {
        String[] parts = slotString.split("-");
        if(parts.length != 2) {
            throw new Exception("Invalid slot format. Expected format: H:mm-H:mm");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime start;
        LocalTime end;
        try {
            start = LocalTime.parse(parts[0].trim(), formatter);
            end = LocalTime.parse(parts[1].trim(), formatter);
        } catch(DateTimeParseException e) {
            throw new Exception("Invalid time format. Use H:mm e.g., 9:30");
        }
        if(Duration.between(start, end).toMinutes() != 30) {
            throw new Exception("Slots are 30 mins only");
        }
        return new TimeSlot(start, end);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof TimeSlot)) return false;
        TimeSlot ts = (TimeSlot) o;
        return startTime.equals(ts.startTime) && endTime.equals(ts.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        return formatter.format(startTime) + "-" + formatter.format(endTime);
    }
}
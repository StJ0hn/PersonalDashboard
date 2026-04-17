package model;

import java.time.LocalDate;

public class StudySession {
    private int durationInMinutes;
    private LocalDate date;
    private String notes;

    public StudySession(int durationInMinutes, String notes) {
        this.durationInMinutes = durationInMinutes;
        this.date = LocalDate.now();
        this.notes = notes;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    @Override
    public String toString() {
        return "StudySession{" +
                "durationInMinutes=" + durationInMinutes +
                ", date=" + date +
                ", notes='" + notes + '\'' +
                '}';
    }
}

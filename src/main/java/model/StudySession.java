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


    @Override
    public String toString() {
        String displayNotes = (notes == null || notes.isBlank()) ? "N/A" : notes; //ternary operator to format notes
        return String.format("[%s] %d min | Notes: %s", date, durationInMinutes, displayNotes);
    }
}

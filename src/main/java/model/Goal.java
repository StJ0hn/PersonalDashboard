package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Goal {
    private String title;
    private String areaOfKnowledge;
    private int targetMinutes;
    private LocalDate deadline;
    private ArrayList<StudySession> historyOfSessions;

    public Goal(String title, String areaOfKnowledge, int targetHours, LocalDate deadline) {
        this.title = title;
        this.areaOfKnowledge = areaOfKnowledge;
        this.targetMinutes = targetHours * 60 ;
        this.deadline = deadline;
        this.historyOfSessions = new ArrayList<>(); 
    }

    public String getTitle() {
        return title;
    }

    public String getAreaOfKnowledge() {
        return areaOfKnowledge;
    }

    public int getTargetMinutes() {
        return targetMinutes;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public ArrayList<StudySession> getHistoryOfSessions() {
        return historyOfSessions;
    }

    public void addStudySession(StudySession session){
        historyOfSessions.add(session);
    }

    public int getAccumulatedMinutes(){
        int sum = 0;
        for (StudySession session: historyOfSessions){
            sum += session.getDurationInMinutes();
        }
        return sum;
    }

    public int getRemainingMinutes() {
        int remaining = targetMinutes - getAccumulatedMinutes();
        return Math.max(0, remaining);
    }

    public double getProgressPercentage() {
        if (targetMinutes == 0) return 0.0;
        return ((double) getAccumulatedMinutes() / targetMinutes) * 100.0;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "title='" + title + '\'' +
                ", areaOfKnowledge='" + areaOfKnowledge + '\'' +
                ", targetMinutes=" + targetMinutes +
                ", deadline=" + deadline +
                ", historyOfGoals=" + historyOfSessions +
                '}';
    }
}

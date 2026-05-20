package controller;

import model.Goal;
import model.StudySession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalController {
    private List<Goal> goals;

    public GoalController(){
        this.goals = new ArrayList<>();
    }

    public void addGoal(String title, String areaOfKnowledge, int targetHours, LocalDate deadline){
        Goal newGoal = new Goal(title, areaOfKnowledge, targetHours,deadline);
        goals.add(newGoal);
    }

    public Goal findGoalByTitle(String title){
        for (Goal goal : goals){
            if (goal.getTitle().equalsIgnoreCase(title)){
                return goal;
            }
        }
        return null;
    }

    public boolean addStudySession(String goalTitle, int duration, String notes){
        Goal goal = findGoalByTitle(goalTitle);
        if (goal != null){
            StudySession session = new StudySession(duration, notes);
            goal.addStudySession(session);
            return true;
        }
        return false;
    }

    public List<Goal> listAllGoals(){
        return goals;
    }
}

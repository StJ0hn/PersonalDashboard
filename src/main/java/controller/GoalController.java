package controller;

import model.Goal;
import model.StudySession;
import repository.GoalJsonRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GoalController {
    private List<Goal> goals;
    private GoalJsonRepository repository;

    public GoalController(){
        this.repository = new GoalJsonRepository();
        this.goals = repository.loadGoals();
    }

    public void addGoal(String title, String areaOfKnowledge, int targetHours, LocalDate deadline){
        Goal newGoal = new Goal(title, areaOfKnowledge, targetHours,deadline);
        goals.add(newGoal);
        repository.saveGoals(goals);
    }

    public Goal findGoalByTitle(String title){
        return goals.stream()
                .filter(goal -> goal.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Goal not found.")); // Ou cria uma GoalNotFoundException!
    }

    public boolean addStudySession(String goalTitle, int duration, String notes){
        Goal goal = findGoalByTitle(goalTitle);
        if (goal != null){
            StudySession session = new StudySession(duration, notes);
            goal.addStudySession(session);
            repository.saveGoals(this.goals);
            return true;
        }
        return false;
    }

    public List<Goal> listAllGoals(){
        return goals;
    }
}

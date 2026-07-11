package controller;

import model.Goal;
import model.StudySession;
import model.Task;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class DashboardController {

    public long countPendingTasks(List<Task> tasks){
        return tasks.stream().filter(task -> task.getTaskStatus().equals(TaskStatus.PENDING)).count();
    }

    public long countCompletedTasks(List<Task> tasks){
        return tasks.stream().filter(task -> task.getTaskStatus().equals(TaskStatus.COMPLETED)).count();
    }

    public long countOverdueTasks(List<Task> tasks){
        return tasks.stream().filter(task -> task.getTaskStatus().equals(TaskStatus.PENDING) && task.getDueDate().isBefore(LocalDate.now())).count();
    }

    public Map<String, Long> countTasksByCategory(List<Task> tasks) {
        return tasks.stream().collect(Collectors.groupingBy(Task::getCategory, Collectors.counting()));
    }

    public Map<String, Integer> calculateStudyMinutesByArea(List<Goal> goals){
        return goals.stream().collect(Collectors.groupingBy(Goal::getAreaOfKnowledge, Collectors.summingInt(Goal::getAccumulatedMinutes)));
    }

    public int calculateCurrentStreak(List<Goal> goals) {
        Set<LocalDate> studyDates = goals.stream()
                .flatMap(goal -> goal.getHistoryOfSessions().stream())
                .map(StudySession::getDate)
                .collect(Collectors.toSet());

        if (studyDates.isEmpty()){
            return 0;
        }

        LocalDate today = LocalDate.now();
        LocalDate dayToCheck = today;

        if (!studyDates.contains(today) && studyDates.contains(today.minusDays(1))){
            dayToCheck = dayToCheck.minusDays(1);
        }

        int streak = 0;

        while (studyDates.contains(dayToCheck)){
            streak++;
            dayToCheck = dayToCheck.minusDays(1);
        }
        return streak;
    }
}

package controller;

import model.Task;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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

}

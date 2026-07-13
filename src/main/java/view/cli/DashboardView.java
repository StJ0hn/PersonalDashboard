package view.cli;

import controller.DashboardController;
import controller.GoalController;
import controller.TaskController;
import model.Goal;
import model.Task;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DashboardView {
    private Scanner sc;
    private DashboardController dashboardController;
    private TaskController taskController;
    private GoalController goalController;

    public DashboardView(Scanner scanner, TaskController taskController, GoalController goalController) {
        this.sc = scanner;
        this.taskController = taskController;
        this.goalController = goalController;
        this.dashboardController = new DashboardController();
    }

    public void showDashboardMenu() {
        System.out.println("\n=== DASHBOARD ===");

        List<Task> allTasks = taskController.listAllTasks();
        List<Goal> allGoals = goalController.listAllGoals();

        if (allTasks.isEmpty() && allGoals.isEmpty()) {
            System.out.println("There is no data available yet.");
            return;
        }

        System.out.println("\nTASK STATISTICS:");
        if (!allTasks.isEmpty()) {
            long pending = dashboardController.countPendingTasks(allTasks);
            long completed = dashboardController.countCompletedTasks(allTasks);
            long overdue = dashboardController.countOverdueTasks(allTasks);

            System.out.println("Pending tasks: " + pending);
            System.out.println("Completed tasks: " + completed);
            System.out.println("Overdue tasks: " + overdue);

            System.out.println("\nTASKS BY CATEGORY:");
            Map<String, Long> categoryMap = dashboardController.countTasksByCategory(allTasks);
            categoryMap.forEach((category, quantity) ->
                    System.out.println(" - " + category + ": " + quantity + " tasks")
            );
        } else {
            System.out.println("No tasks registered yet.");
        }

        System.out.println("\nSTUDY STATISTICS:");
        if (!allGoals.isEmpty()) {
            int streak = dashboardController.calculateCurrentStreak(allGoals);
            System.out.println("Current Streak: " + streak + " days");

            System.out.println("\nMINUTES STUDIED BY AREA:");
            Map<String, Integer> minutesByArea = dashboardController.calculateStudyMinutesByArea(allGoals);

            if (minutesByArea.isEmpty() || minutesByArea.values().stream().mapToInt(Integer::intValue).sum() == 0) {
                System.out.println("No study sessions registered yet.");
            } else {
                minutesByArea.forEach((area, minutes) ->
                        System.out.println(" - " + area + ": " + minutes + " minutes")
                );
            }
        } else {
            System.out.println("No study goals registered yet.");
        }

        System.out.println("=====================");
    }
}
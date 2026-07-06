package view;

import controller.DashboardController;
import controller.TaskController;
import model.Task;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DashboardView {
    private Scanner sc;
    private DashboardController dashboardController;
    private TaskController taskController;

    public DashboardView(Scanner scanner, TaskController taskController) {
        this.sc = scanner;
        this.taskController = taskController;
        this.dashboardController = new DashboardController();
    }

    public void showDashboardMenu() {
        System.out.println("\n=== DASHBOARD ===");
        List<Task> allTasks = taskController.listAllTasks();

        if (allTasks.isEmpty()) {
            System.out.println("There is no data available yet.");
            return;
        }

        long pending = dashboardController.countPendingTasks(allTasks);
        long completed = dashboardController.countCompletedTasks(allTasks);
        long overdue = dashboardController.countOverdueTasks(allTasks);

        System.out.println("Pending tasks: " + pending);
        System.out.println("Completed tasks: " + completed);
        System.out.println("Overdue tasks: " + overdue);

        System.out.println("\nTASKS BY CATEGORY:");
        Map<String, Long> categoryMap = dashboardController.countTasksByCategory(allTasks);
        categoryMap.forEach((category, quantity) ->
                System.out.println(category + ": " + quantity + " tasks")
        );
        System.out.println("=====================");
    }
}

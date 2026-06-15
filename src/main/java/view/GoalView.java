package view;

import controller.GoalController;
import model.Goal;
import model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class GoalView {
    private Scanner sc;
    private GoalController controller;

    public GoalView(Scanner scanner) {
        this.sc = scanner;
        this.controller = new GoalController();
    }

    public void showGoalMenu() {
        while (true) {
            System.out.println("\n=== MENU OF STUDY GOALS ===");
            System.out.println("[1] CREATE GOAL");
            System.out.println("[2] REGISTER STUDY SESSION");
            System.out.println("[3] VISUALIZE PROGRESS");
            System.out.println("[0] LEAVE TO MAIN MENU");
            System.out.print("Choice: ");

            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1:
                    createGoalUI();
                    break;
                case 2:
                    registerStudySessionUI();
                    break;
                case 3:
                    listGoalsUI();
                    break;
                case 0:
                    return; // Volta para o menu principal
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void createGoalUI() {
        System.out.print("Provide a title for the goal: ");
        String title = sc.nextLine();

        System.out.print("Provide an area of knowledge: ");
        String area = sc.nextLine();

        System.out.print("Enter a target number of hours: ");
        int hours = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the deadline (yyyy-MM-dd): ");
        LocalDate deadline = LocalDate.parse(sc.nextLine());

        controller.addGoal(title, area, hours, deadline);
        System.out.println("Study goal created successfully!");
    }

    private void registerStudySessionUI() {
        List<Goal> currentGoals = controller.listAllGoals();
        if (currentGoals.isEmpty()) {
            System.out.println("No goals registered yet. Please create a goal first.");
            return;
        }

        System.out.print("Enter the title of the Goal: ");
        String goalTitle = sc.nextLine();

        System.out.print("Duration (in minutes): ");
        int duration = Integer.parseInt(sc.nextLine());

        System.out.print("Notes (optional): ");
        String notes = sc.nextLine();

        boolean success = controller.addStudySession(goalTitle, duration, notes);
        if (success) {
            System.out.println("Study session registered successfully!");
        } else {
            System.out.println("Goal not found. Please check the title and try again.");
        }
    }

    private void listGoalsUI() {
        List<Goal> currentGoals = controller.listAllGoals();
        if (currentGoals.isEmpty()) {
            System.out.println("No goals found.");
            return;
        }
        System.out.printf("%-20s | %-50s | %-15s | %-10s%n", "TITLE", "AREA OF KNOWLEDGE" ,"TARGET MINUTES", "DEADLINE");
        System.out.println("-".repeat(150));
        for (Goal goal : currentGoals) {
            System.out.printf("%-20s | %-50S |%-15s | %-10s%n", goal.getTitle(), goal.getAreaOfKnowledge(), goal.getTargetMinutes(), goal.getDeadline());
        }
        System.out.printf("-".repeat(150));
    }
}
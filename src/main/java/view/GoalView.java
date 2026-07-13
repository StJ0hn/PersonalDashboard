package view;

import controller.GoalController;
import model.Goal;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static util.InputUtils.*;

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

            int option = readIntegerNumber(this.sc, "Choice: ");

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
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createGoalUI() {
        String title = readRequiredString(this.sc, "Enter the title of the Goal: ");

        String area = readRequiredString(this.sc, "Provide an area of knowledge: ");

        int hours = readIntegerNumber(this.sc, "Enter a target number of hours: ");

        LocalDate deadline = readFutureDate(this.sc, "Enter the deadline (yyyy-MM-dd): ");

        controller.addGoal(title, area, hours, deadline);
        System.out.println("Study goal created successfully!");
    }

    private void registerStudySessionUI() {
        List<Goal> currentGoals = controller.listAllGoals();
        if (currentGoals.isEmpty()) {
            System.out.println("No goals registered yet. Please create a goal first.");
            return;
        }

        String goalTitle = readRequiredString(this.sc, "Enter the title of the Goal: ");

        int duration = readIntegerNumber(this.sc,"Duration (in minutes): ");

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
            System.out.println("No goals registered yet.");
            return;
        }

        System.out.print("Enter goal title or part of it: ");
        String searchTerm = sc.nextLine().trim();

        List<Goal> foundGoals = currentGoals.stream()
                .filter(g -> g.getTitle().toLowerCase().contains(searchTerm.toLowerCase()))
                .toList();

        if (foundGoals.isEmpty()) {
            System.out.println("Goal not found.");
            return;
        }

        System.out.printf("%-20s | %-30s | %-15s | %-15s | %-15s | %-10s | %-10s%n",
                "TITLE", "AREA OF KNOWLEDGE", "TARGET(MIN)", "ACCUMULATED", "REMAINING(MIN)", "PROGRESS", "DEADLINE");
        System.out.println("-".repeat(130));

        for (Goal goal : foundGoals) {
            double percentage = goal.getTargetMinutes() == 0 ? 0.0 : ((double) goal.getAccumulatedMinutes() / goal.getTargetMinutes()) * 100.0;
            String progressStr = String.format("%.2f%%", percentage);

            System.out.printf("%-20s | %-30S | %-15d | %-15d | %-15d | %-10s | %-10s%n",
                    goal.getTitle(),
                    goal.getAreaOfKnowledge(),
                    goal.getTargetMinutes(),
                    goal.getAccumulatedMinutes(),
                    goal.getRemainingMinutes(),
                    progressStr,
                    goal.getDeadline());

            if (goal.getHistoryOfSessions().isEmpty()) {
                System.out.println("   -> No study sessions registered.");
            } else {
                System.out.println("   -> STUDY SESSIONS:");
                goal.getHistoryOfSessions().forEach(session -> System.out.println("      " + session.toString()));
            }
            System.out.println("-".repeat(130));
        }
    }
}
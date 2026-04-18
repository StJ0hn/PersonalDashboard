import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.StudySession;
import model.Task;
import model.Goal;
import model.TaskPriority;


public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Goal> goals = new ArrayList<>();

    public static void createTask() {
        //TODO Pedir dados pro usuário -> instanciar Task -> adicionar na lista 'tasks'
        System.out.print("Provide a title for the task: ");
        String title = sc.nextLine();
        System.out.print("Provide a description for the task: ");
        String description = sc.nextLine();
        System.out.print("Select priority [1 - High] [2 - Medium] [3 - Low]: ");
        TaskPriority priority;
        while (true){
            int optionPriority = Integer.parseInt(sc.nextLine());
            if (optionPriority == 1){
                priority = TaskPriority.HIGH;
                break;
            } else if (optionPriority == 2) {
                priority = TaskPriority.MEDIUM;
                break;
            } else if (optionPriority == 3) {
                priority = TaskPriority.LOW;
                break;
            }
            else {
                System.out.print("Invalid option. Try again.");
            }
        }
        System.out.print("Provide a category for the task: ");
        String category = sc.nextLine();
        System.out.print("Enter the task expiration date (yyyy-MM-dd): ");
        String input = sc.nextLine();
        LocalDate dueDate = LocalDate.parse(input);
        Task newTask = new Task(title, description, priority, category, dueDate);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    public static void createGoal() {
        System.out.print("Provide a title for the goal: ");
        String title = sc.nextLine();
        System.out.println("Provide an area of knowledge of the goal: ");
        String areaOfKnowledge = sc.nextLine();
        System.out.println("Enter a target number of hours for the goal: ");
        int targetHours = Integer.parseInt(sc.nextLine());
        System.out.println("Enter the deadline for achieving the goal (yyyy-MM-dd): ");
        String input = sc.nextLine();
        LocalDate deadline = LocalDate.parse(input);
        Goal goal = new Goal(title, areaOfKnowledge, targetHours, deadline);
        goals.add(goal);
        System.out.println("Study goal created!");
    }

    public static void listAll() {
        for (Task task:tasks){
            System.out.println(task);
        }

        for (Goal goal:goals){
            System.out.println(goal);
        }
    }

    public static void completeTask() {
        System.out.print("Enter the title of the task to be marked as completed: ");
        String titleTask = sc.nextLine();
        for (Task task:tasks){
            if (task.getTitle().equals(titleTask)){
                task.markAsCompleted();
                System.out.println("The task: " + task.getTitle() + " was marked as completed!");
            }
        }
    }

    public static void registerStudySession() {
        System.out.println("\n--- REGISTER STUDY SESSION ---");

        if (goals.isEmpty()) {
            System.out.println("No goals registered yet. Please create a goal first [Press 2].");
            return; // return to end the method and return to main menu
        }

        System.out.print("Enter the title of the Goal: ");
        String searchTitle = sc.nextLine();
        boolean found = false;

        for (Goal currentGoal : goals) {
            if (currentGoal.getTitle().equalsIgnoreCase(searchTitle)) {
                found = true;
                System.out.print("Duration (in minutes): ");
                int duration = Integer.parseInt(sc.nextLine());
                System.out.print("Notes (optional): ");
                String notes = sc.nextLine();
                StudySession newSession = new StudySession(duration, notes);
                currentGoal.addStudySession(newSession);

                System.out.println("Study session registered successfully!");
                break;
            }
        }

        if (!found) {
            System.out.println("Goal not found. Please check the title and try again.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("=== PERSONAL DASHBOARD ===");
            System.out.println("[1] Create Task");
            System.out.println("[2] Create Goal");
            System.out.println("[3] Register Study Session");
            System.out.println("[4] List All");
            System.out.println("[5] Finish Task");
            System.out.println("[0] Exit");

            System.out.print("Select Option: ");
            int option = Integer.parseInt(sc.nextLine());
            switch (option){
                case 1:
                    createTask();
                    break;
                case 2:
                    createGoal();
                    break;
                case 3:
                    registerStudySession();
                    break;
                case 4:
                    listAll();
                    break;
                case 5:
                    completeTask();
                    break;
                case 0:
                    System.out.print("Ending System...");
                    System.exit(0);
            }
        }
    }
}
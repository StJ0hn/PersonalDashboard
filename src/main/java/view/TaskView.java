package view;

import controller.TaskController;
import model.Task;
import model.TaskPriority;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;



public class TaskView {
    private Scanner sc;
    private TaskController taskController;

    public TaskView (Scanner scanner){
        this.sc = scanner;
        taskController = new TaskController();
    }

    public void showTaskMenu(){
        while (true){
            System.out.println("\n=== TASK MENU ===");
            System.out.println("[1] - CREATE TASK");
            System.out.println("[2] - LIST ALL TASKS");
            System.out.println("[3] - MARK TASK AS 'COMPLETED'");
            System.out.println("[0] - LEAVE TO MAIN MENU");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 1:
                    createTaskUI();
                    break;
                case 2:
                    listTaskUI();
                    break;
                case 3:
                    completeTaskUI();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
    private void createTaskUI(){
        System.out.print("Provide a title for the task: ");
        String title = sc.nextLine();

        System.out.println("Provide a description for the task: ");
        String description = sc.nextLine();

        System.out.print("Select priority [1 - High] [2 - Medium] [3 - Low]: ");
        TaskPriority priority = null;
        while (priority == null) {
            int optionPriority = Integer.parseInt(sc.nextLine());
            if (optionPriority == 1) priority = TaskPriority.HIGH;
            else if (optionPriority == 2) priority = TaskPriority.MEDIUM;
            else if (optionPriority == 3) priority = TaskPriority.LOW;
            else System.out.print("Invalid option. Try again: ");
        }

        System.out.print("Provide a category for the task: ");
        String category = sc.nextLine();

        System.out.print("Enter the task expiration date (yyyy-MM-dd): ");
        LocalDate dueDate = LocalDate.parse(sc.nextLine());

        taskController.addTasks(title, description, priority, category, dueDate);
        System.out.println("Task added successfully!");
    }

    private void listTaskUI(){
        List<Task> currentTasks = taskController.listAllTasks();
        if (currentTasks.isEmpty()){
            System.out.println("No tasks found.");
            return;
        }
        for (Task task : currentTasks){
            System.out.println(task);
        }
    }

    private void completeTaskUI(){
        System.out.println("Enter the title of the task to be marked as completed: ");
        String titleTask = sc.nextLine();
        boolean success = taskController.completeTask(titleTask);

        if (success){
            System.out.println("The task: " + titleTask + " was marked as completed!");
        }
    }
}

package view;

import controller.TaskController;
import model.Task;
import model.TaskPriority;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static util.InputUtils.*;


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
            System.out.println("[4] - DELETE A TASK");
            System.out.println("[5] - UPDATE A TASK");
            System.out.println("[0] - LEAVE TO MAIN MENU");
            int option = readIntegerNumber(this.sc, "Choice: ");
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
                case 4:
                    removeTaskUI();
                    break;
                case 5:
                    updateTaskUI();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid Option!");
            }
        }
    }
    private void createTaskUI(){
        String title = readRequiredString(this.sc, "Provide a title for the task: ");

        System.out.println("Provide a description for the task: ");
        String description = sc.nextLine();

        TaskPriority priority = readTaskPriority(this.sc, "Select priority [1 - High] [2 - Medium] [3 - Low]: ");

        String category = readRequiredString(this.sc, "Provide a category for the task: ");

        LocalDate dueDate = readFutureDate(this.sc, "Enter the task expiration date (yyyy-MM-dd): ");

        taskController.addTasks(title, description, priority, category, dueDate);
        System.out.println("Task added successfully!");
    }

    private void listTaskUI(){
        List<Task> currentTasks = taskController.listAllTasks();
        if (currentTasks.isEmpty()){
            System.out.println("No tasks found.");
            return;
        }
        System.out.printf("%-20s | %-50s | %-15s | %-10s | %-10s | %-15s%n", "TITLE", "DESCRIPTION" ,"CATEGORY", "PRIORITY", "DEADLINE", "STATUS");
        System.out.println("-".repeat(150));
        for (Task task : currentTasks){
            System.out.printf("%-20s | %-50S |%-15s | %-10s | %-10s | %-15s%n", task.getTitle(), task.getDescription() ,task.getCategory(), task.getTaskPriority(), task.getDueDate(), task.getTaskStatus());
        }
        System.out.printf("-".repeat(150));
    }

    private void removeTaskUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to remove.");
            return;
        }
        String taskHasBeRemoved = readRequiredString(this.sc, "Enter the title of the task to be removed: ");
        boolean success = taskController.deleteTask(taskHasBeRemoved);
        if (success){
            System.out.println("Task: " + taskHasBeRemoved + " has been removed.");
        }
        else {
            System.out.println("Task not found!");
        }
    }

    private void updateTaskUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to update.");
            return;
        }
        String searchTitle = readRequiredString(this.sc, "Type the title of task to be updated: ");
        Task taskToEdit = taskController.findTaskByTitle(searchTitle);
        if (taskToEdit == null){
            System.out.println("Task not found.");
        }
        String newDescription = readRequiredString(this.sc, "Type new description: ");
        String newCategory = readRequiredString(this.sc, "Type new category: ");
        LocalDate newDueDate = readFutureDate(this.sc, "Type new due date: ");
        TaskPriority newPriority = readTaskPriority(this.sc, "Type new priority of the task [1 - High] [2 - Medium] [3 - Low]: ");
        boolean success = taskController.editTask(searchTitle, newDescription, newCategory, newDueDate, newPriority);
        if (success){
            System.out.println("Task successfully updated.");
        }
        else {
            System.out.println("Unable to update the task. Please try again.");
        }
    }

    private void completeTaskUI(){
        String titleTask = readRequiredString(this.sc, "Enter the title of the task to be marked as completed: ");
        boolean success = taskController.completeTask(titleTask);

        if (success){
            System.out.println("The task: " + titleTask + " was marked as completed!");
        }
        else {
            System.out.println("Task not found! Please check the title.");
        }
    }
}

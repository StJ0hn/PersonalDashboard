package view.cli;

import controller.TaskController;
import exception.TaskNotFoundException;
import model.Task;
import model.TaskPriority;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static util.InputUtils.*;


public class TaskView {
    private Scanner sc;
    private TaskController taskController;

    public TaskView (Scanner scanner, TaskController taskController){
        this.sc = scanner;
        this.taskController = taskController;
    }

    public void showTaskMenu(){
        while (true){
            System.out.println("\n=== TASK MENU ===");
            System.out.println("[1] - CREATE TASK");
            System.out.println("[2] - LIST ALL TASKS");
            System.out.println("[3] - MARK TASK AS 'COMPLETED'");
            System.out.println("[4] - DELETE A TASK");
            System.out.println("[5] - UPDATE A TASK");
            System.out.println("[6] - SEARCH TASK BY TITLE");
            System.out.println("[7] - FILTER TASK");
            System.out.println("[8] - EXPORT TO CSV");
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
                case 6:
                    searchTaskUI();
                    break;
                case 7:
                    optionFilterUI();
                    break;
                case 8:
                    exportCsvUI();
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
        printTaskTable(currentTasks);
    }

    //Helper method to format table of tasks
    private void printTaskTable(List<Task> tasksToPrint) {
        if (tasksToPrint.isEmpty()){
            System.out.println("No tasks found.");
            return;
        }

        System.out.printf("%-20s | %-50s | %-15s | %-10s | %-10s | %-15s%n", "TITLE", "DESCRIPTION" ,"CATEGORY", "PRIORITY", "DEADLINE", "STATUS");
        System.out.println("-".repeat(150));

        for (Task task : tasksToPrint){
            System.out.printf("%-20s | %-50S |%-15s | %-10s | %-10s | %-15s%n", task.getTitle(), task.getDescription() ,task.getCategory(), task.getTaskPriority(), task.getDueDate(), task.getTaskStatus());
        }

        System.out.println("-".repeat(150));
    }

    private void removeTaskUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to remove.");
            return;
        }
        try {
            String taskHasBeRemoved = readRequiredString(this.sc, "Enter the title of the task to be removed: ");
            taskController.deleteTask(taskHasBeRemoved);
            System.out.println("Task removed successfully.");
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
        }
    }

    private void updateTaskUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to update.");
            return;
        }
        try {
            String searchTitle = readRequiredString(this.sc, "Type the title of task to be updated: ");
            taskController.findTaskByTitle(searchTitle);
            String newDescription = readRequiredString(this.sc, "Type new description: ");
            String newCategory = readRequiredString(this.sc, "Type new category: ");
            LocalDate newDueDate = readFutureDate(this.sc, "Type new due date: ");
            TaskPriority newPriority = readTaskPriority(this.sc, "Type new priority of the task [1 - High] [2 - Medium] [3 - Low]: ");
            taskController.editTask(searchTitle, newDescription, newCategory, newDueDate, newPriority);
            System.out.println("Task successfully updated.");
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
        }
    }

    private void searchTaskUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to search.");
            return;
        }
        try {
            String keyword = readRequiredString(this.sc, "Enter a snippet of the task to be searched for: ");
            List<Task> foundTasks = taskController.searchTasksByTitle(keyword);
            printTaskTable(foundTasks);
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
        }
    }

    private void optionFilterUI(){
        if (taskController.listAllTasks().isEmpty()){
            System.out.println("Has no tasks to filter.");
            return;
        }
        try {
            int option = readIntegerNumber(this.sc, "Filter by ([1] - STATUS, [2] - PRIORITY, 3 - CATEGORY): ");
            if (option == 1) {
                int status = readIntegerNumber(this.sc, "Insert status ([1] - PENDING/ [2] - COMPLETED): ");
                if (status == 1) {
                    printTaskTable(taskController.filterTasksByStatus(TaskStatus.PENDING));
                } else if (status == 2) {
                    printTaskTable(taskController.filterTasksByStatus(TaskStatus.COMPLETED));
                } else {
                    System.out.println("Invalid option.");
                }
            } else if (option == 2) {
                TaskPriority priority = readTaskPriority(this.sc, "Insert priority ([1] - HIGH, [2] - MEDIUM, [3] - LOW): ");
                printTaskTable(taskController.filterTasksByPriority(priority));
            } else if (option == 3) {
                String category = readRequiredString(this.sc, "Insert the category of task: ");
                printTaskTable(taskController.filterTasksByCategory(category));
            } else {
                System.out.println("Invalid option");
            }
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
        }
    }

    private void completeTaskUI(){
        try {
            String titleTask = readRequiredString(this.sc, "Enter the title of the task to be marked as completed: ");
            taskController.completeTask(titleTask);
            System.out.println("Task marked as Completed successfully.");
        }
        catch (TaskNotFoundException taskNotFoundException){
            System.out.println(taskNotFoundException.getMessage());
        }
    }

    private void exportCsvUI(){
        List<Task> allTasks = taskController.listAllTasks();
        if (allTasks.isEmpty()) {
            System.out.println("There are no tasks to export.");
            return;
        }

        String fileName = readRequiredString(this.sc, "Enter the name for the CSV file (e.g., 'my_tasks'): ");
        util.CsvExporter.exportTasksToCSV(allTasks, fileName);
        System.out.println("CSV file generated successfully! Look for '" + fileName + ".csv' in your project folder.");
    }
}

package controller;

import model.Task;
import model.TaskPriority;
import model.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private ArrayList<Task> tasks;
    public TaskController (){
        tasks = new ArrayList<>();
    }

    public void addTasks(String title, String description, TaskPriority priority, String category, LocalDate dueDate){
        Task task = new Task(title, description, priority, category, dueDate);
        tasks.add(task);
    }

    public boolean deleteTask(String title){
        return tasks.removeIf(task -> title.equalsIgnoreCase(task.getTitle()));
    }

    public List<Task> listAllTasks(){
        return tasks;
    }

    public Task findTaskByTitle(String titleSearch){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(titleSearch)){
                return task;
            }
        }
        return null;
    }

    public List<Task> searchTasksByTitle(String keyword){
        return tasks.stream().filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    public boolean editTask(String titleFind, String newDescription, String newCategory, LocalDate newDueDate, TaskPriority newPriority){
        Task task = findTaskByTitle(titleFind);
        if (task == null){
            return false;
        }
        task.updateTask(newDescription, newCategory, newDueDate, newPriority);
        return true;
    }

    public boolean completeTask(String taskName){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(taskName)){
                task.markAsCompleted();
                return true;
            }
        }
        return false;
    }
}

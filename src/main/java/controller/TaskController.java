package controller;

import model.Task;
import model.TaskPriority;

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

    public List<Task> listAllTasks(){
        return tasks;
    }

    public boolean completeTask(String taskName){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(taskName)){
                return true;
            }
        }
        return false;
    }
}

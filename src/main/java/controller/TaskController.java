package controller;

import exception.TaskNotFoundException;
import model.Task;
import model.TaskPriority;
import model.TaskStatus;
import repository.TaskJsonRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskController {
    private List<Task> tasks;
    private TaskJsonRepository repository;

    public TaskController (){
        this.repository = new TaskJsonRepository();
        this.tasks = repository.loadTasks();
    }

    public void addTasks(String title, String description, TaskPriority priority, String category, LocalDate dueDate){
        Task task = new Task(title, description, priority, category, dueDate);
        this.tasks.add(task);
        repository.saveTasks(this.tasks);
    }

    public void deleteTask(String title){
        boolean wasRemoved = tasks.removeIf(task -> title.equalsIgnoreCase(task.getTitle()));

        if (!wasRemoved) {
            throw new TaskNotFoundException("Task not found.");
        }
        repository.saveTasks(this.tasks);
    }

    public List<Task> listAllTasks(){
        return tasks;
    }

    public Task findTaskByTitle(String titleSearch){
        return tasks.stream().filter(task -> task.getTitle().equalsIgnoreCase(titleSearch)).findFirst().orElseThrow(() -> new TaskNotFoundException("Task not found."));
    }

    public List<Task> searchTasksByTitle(String keyword){
        return tasks.stream().filter(task -> task.getTitle().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    public void editTask(String titleFind, String newDescription, String newCategory, LocalDate newDueDate, TaskPriority newPriority){
        Task task = findTaskByTitle(titleFind);
        task.updateTask(newDescription, newCategory, newDueDate, newPriority);
        repository.saveTasks(this.tasks);
    }

    public List<Task> filterTasksByStatus(TaskStatus status) {
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.getTaskStatus().equals(status))
                .toList();
        if (filteredTasks.isEmpty()){ //Deliberately duplicated until a stable abstraction emerges.
            throw new TaskNotFoundException("No tasks found for the specified filter.");
        }
        return filteredTasks;
    }

    public List<Task> filterTasksByPriority(TaskPriority priority){
        List<Task> filteredTasks = tasks.stream().filter(task -> task.getTaskPriority().equals(priority)).toList();
        if (filteredTasks.isEmpty()){ //Deliberately duplicated until a stable abstraction emerges.
            throw new TaskNotFoundException("No tasks found for the specified filter.");
        }
        return filteredTasks;
    }

    public List<Task> filterTasksByCategory(String category){
        List<Task> filteredTasks = tasks.stream().filter(task -> task.getCategory().equalsIgnoreCase(category)).toList();
        if (filteredTasks.isEmpty()){ //Deliberately duplicated until a stable abstraction emerges.
            throw new TaskNotFoundException("No tasks found for the specified filter.");
        }
        return filteredTasks;
    }

    public boolean completeTask(String taskName){
        for (Task task : tasks){
            if (task.getTitle().equalsIgnoreCase(taskName)){
                task.markAsCompleted();
                repository.saveTasks(tasks);
                return true;
            }
        }
        throw new TaskNotFoundException("Task not found");
    }
}

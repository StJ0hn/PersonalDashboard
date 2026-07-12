package model;

import java.time.LocalDate;

public class Task {
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private TaskPriority taskPriority;
    private String category;
    private LocalDate dueDate;
    private LocalDate completedAt;

    public Task(String title, String description, TaskPriority taskPriority, String category, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.taskStatus = TaskStatus.PENDING;
        this.taskPriority = taskPriority;
        this.category = category;
        this.dueDate = dueDate;
        completedAt = null;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }


    public String getCategory() {
        return category;
    }

    public LocalDate getCompletedAt(){
        return completedAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void markAsCompleted(){
        setTaskStatus(TaskStatus.COMPLETED);
        completedAt = LocalDate.now();
    }

    public void updateTask(String newDescription, String newCategory, LocalDate newDueDate, TaskPriority newPriority){
        this.description = newDescription;
        this.category = newCategory;
        this.dueDate = newDueDate;
        this.taskPriority = newPriority;
    }


    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", category='" + category + '\'' +
                ", dueDate=" + dueDate +
                ", completedAt=" + completedAt +
                '}';
    }
}

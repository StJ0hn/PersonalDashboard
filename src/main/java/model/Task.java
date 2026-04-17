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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void markAsCompleted(){
        setTaskStatus(TaskStatus.COMPLETED);
        completedAt = LocalDate.now();
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

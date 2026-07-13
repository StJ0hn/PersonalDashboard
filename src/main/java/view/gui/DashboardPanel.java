package view.gui;

import controller.DashboardController;
import controller.GoalController;
import controller.TaskController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Goal;
import model.Task;

import java.util.List;

public class DashboardPanel {

    private DashboardController dashboardController;
    private TaskController taskController;
    private GoalController goalController;

    public DashboardPanel(DashboardController dashboardController, TaskController taskController, GoalController goalController) {
        this.dashboardController = dashboardController;
        this.taskController = taskController;
        this.goalController = goalController;
    }

    public VBox getView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #121212;");

        Label titleLabel = new Label("OVERVIEW");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        HBox statsContainer = new HBox(20);
        statsContainer.setAlignment(Pos.CENTER_LEFT);

        List<Task> tasks = taskController.listAllTasks();
        List<Goal> goals = goalController.listAllGoals();

        long currentStreak = dashboardController.calculateCurrentStreak(goals);
        long pendingTasks = dashboardController.countPendingTasks(tasks);
        long totalGoals = goals.size();

        VBox streakCard = createStatCard("CURRENT STREAK", currentStreak + " DAYS", "#e74c3c");
        VBox tasksCard = createStatCard("PENDING TASKS", String.valueOf(pendingTasks), "#3498db");
        VBox goalsCard = createStatCard("TOTAL GOALS", String.valueOf(totalGoals), "#2ecc71");

        statsContainer.getChildren().addAll(streakCard, tasksCard, goalsCard);
        root.getChildren().addAll(titleLabel, statsContainer);

        return root;
    }

    private VBox createStatCard(String title, String value, String color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setMinSize(200, 100);
        card.setStyle("-fx-background-color: #1e1e1e; -fx-background-radius: 8px; -fx-border-color: " + color + "; -fx-border-radius: 8px; -fx-border-width: 2px;");
        card.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #aaaaaa;");

        Label valueLabel = new Label(value);
        valueLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        card.getChildren().addAll(titleLabel, valueLabel);
        return card;
    }
}
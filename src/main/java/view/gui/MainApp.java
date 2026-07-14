package view.gui;

import controller.DashboardController;
import controller.GoalController;
import controller.TaskController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private BorderPane root;
    private TaskController taskController;
    private GoalController goalController;
    private DashboardController dashboardController;
    private TaskPanel taskPanel;


    @Override
    public void init() {
        taskController = new TaskController();
        goalController = new GoalController();
        dashboardController = new DashboardController();
        taskPanel = new TaskPanel(taskController);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();

        VBox sidebar = new VBox(15);
        sidebar.setPadding(new Insets(30, 20, 20, 20));
        sidebar.setStyle("-fx-background-color: #1e1e1e;");
        sidebar.setPrefWidth(220);

        Button btnDashboard = new Button("Dashboard");
        Button btnTasks = new Button("Manage Tasks");
        Button btnGoals = new Button("Manage Goals");

        String btnStyle = "-fx-background-color: transparent; -fx-text-fill: #b3b3b3; -fx-font-size: 14px; -fx-cursor: hand; -fx-font-weight: bold;";
        btnDashboard.setStyle(btnStyle);
        btnTasks.setStyle(btnStyle);
        btnGoals.setStyle(btnStyle);

        sidebar.getChildren().addAll(btnDashboard, btnTasks, btnGoals);
        root.setLeft(sidebar);

        DashboardPanel dashboardPanel = new DashboardPanel(dashboardController, taskController, goalController);
        root.setCenter(dashboardPanel.getView());

        GoalPanel goalPanel = new GoalPanel(goalController);
        btnGoals.setOnAction(e -> root.setCenter(goalPanel.getView()));

        btnDashboard.setOnAction(e -> root.setCenter(dashboardPanel.getView()));
        btnTasks.setOnAction(e -> root.setCenter(taskPanel.getView()));

        Scene scene = new Scene(root, 1024, 640);
        primaryStage.setTitle("Personal Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
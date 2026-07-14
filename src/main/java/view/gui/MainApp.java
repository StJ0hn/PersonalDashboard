package view.gui;

import controller.DashboardController;
import controller.GoalController;
import controller.TaskController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
        Button btnExport = new Button("Export to CSV");

        String btnStyle = "-fx-background-color: transparent; -fx-text-fill: #b3b3b3; -fx-font-size: 14px; -fx-cursor: hand; -fx-font-weight: bold;";
        btnDashboard.setStyle(btnStyle);
        btnTasks.setStyle(btnStyle);
        btnGoals.setStyle(btnStyle);
        btnExport.setStyle(btnStyle);

        // Cria o Dashboard inicial e coloca-o no centro
        DashboardPanel initialDashboard = new DashboardPanel(dashboardController, taskController, goalController);
        root.setCenter(initialDashboard.getView());
        root.setLeft(sidebar);

        // Ações de Navegação
        GoalPanel goalPanel = new GoalPanel(goalController);

        // Sempre que clicar no Dashboard, gera um novo para ter os cálculos (Streak, Pending) atualizados
        btnDashboard.setOnAction(e -> {
            DashboardPanel freshDashboard = new DashboardPanel(dashboardController, taskController, goalController);
            root.setCenter(freshDashboard.getView());
        });

        btnTasks.setOnAction(e -> root.setCenter(taskPanel.getView()));
        btnGoals.setOnAction(e -> root.setCenter(goalPanel.getView()));

        // Ação de Exportação
        btnExport.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog("backup_tarefas");
            dialog.setTitle("Export Data");
            dialog.setHeaderText("Export Tasks to CSV");
            dialog.setContentText("Enter the file name (without .csv):");
            dialog.getDialogPane().setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");

            dialog.showAndWait().ifPresent(fileName -> {
                util.CsvExporter.exportTasksToCSV(taskController.listAllTasks(), fileName);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Data exported successfully to exports/" + fileName + ".csv");
                alert.getDialogPane().setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");
                alert.showAndWait();
            });
        });

        // Empurra o botão de exportação para o fundo
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        // Adiciona tudo APENAS UMA VEZ à barra lateral
        sidebar.getChildren().addAll(btnDashboard, btnTasks, btnGoals, spacer, btnExport);

        Scene scene = new Scene(root, 1024, 640);
        primaryStage.setTitle("Personal Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
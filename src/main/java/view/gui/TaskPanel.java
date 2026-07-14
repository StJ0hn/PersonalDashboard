package view.gui;

import controller.TaskController;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Task;
import model.TaskPriority;

public class TaskPanel {

    private TaskController taskController;
    private TableView<Task> table;

    public TaskPanel(TaskController taskController) {
        this.taskController = taskController;
    }

    public VBox getView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #121212;");

        Label titleLabel = new Label("MANAGE TASKS");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Button btnNewTask = new Button("+ New Task");
        btnNewTask.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        btnNewTask.setOnAction(e -> {
            AddTaskDialog dialog = new AddTaskDialog(taskController, this::refreshTable);
            dialog.showAndWait();
        });

        setupTable();
        HBox actionLayout = createActionButtons();

        root.getChildren().addAll(titleLabel, btnNewTask, table, actionLayout);
        refreshTable();

        return root;
    }

    private HBox createForm() {
        HBox form = new HBox(15);

        TextField titleInput = new TextField();
        titleInput.setPromptText("Task Title");
        titleInput.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");

        TextField categoryInput = new TextField();
        categoryInput.setPromptText("Category");
        categoryInput.setStyle("-fx-background-color: #1e1e1e; -fx-text-fill: white;");

        ComboBox<TaskPriority> priorityInput = new ComboBox<>();
        priorityInput.getItems().addAll(TaskPriority.values());
        priorityInput.setPromptText("Priority");
        priorityInput.setStyle("-fx-background-color: #1e1e1e;");

        DatePicker deadlineInput = new DatePicker();
        deadlineInput.setPromptText("Deadline");
        deadlineInput.setStyle("-fx-control-inner-background: #1e1e1e;");

        Button btnAdd = new Button("Add Task");
        btnAdd.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        btnAdd.setOnAction(e -> {
            if (!titleInput.getText().isEmpty() && priorityInput.getValue() != null && deadlineInput.getValue() != null) {
                // Ajustado para o teu método addTasks
                taskController.addTasks(titleInput.getText(), "", priorityInput.getValue(), categoryInput.getText(), deadlineInput.getValue());
                refreshTable();
                titleInput.clear();
                categoryInput.clear();
                priorityInput.setValue(null);
                deadlineInput.setValue(null);
            }
        });

        form.getChildren().addAll(titleInput, categoryInput, priorityInput, deadlineInput, btnAdd);
        return form;
    }

    private void setupTable() {
        table = new TableView<>();
        table.setStyle("-fx-background-color: #1e1e1e; -fx-control-inner-background: #1e1e1e; -fx-text-fill: white;");
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        TableColumn<Task, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Task, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCategory()));

        // Ajustado para o teu método getTaskPriority()
        TableColumn<Task, String> priorityCol = new TableColumn<>("Priority");
        priorityCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTaskPriority().name()));

        TableColumn<Task, String> deadlineCol = new TableColumn<>("Deadline");
        deadlineCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDueDate().toString()));

        // Ajustado para o teu método getTaskStatus()
        TableColumn<Task, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTaskStatus().name()));

        table.getColumns().addAll(titleCol, categoryCol, priorityCol, deadlineCol, statusCol);
    }

    private HBox createActionButtons() {
        HBox actionLayout = new HBox(15);

        Button btnComplete = new Button("Mark as Completed");
        btnComplete.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");
        btnComplete.setOnAction(e -> {
            Task selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                taskController.completeTask(selected.getTitle());
                refreshTable();
            }
        });

        Button btnDelete = new Button("Delete Task");
        btnDelete.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");
        btnDelete.setOnAction(e -> {
            Task selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Ajustado para o teu método deleteTask
                taskController.deleteTask(selected.getTitle());
                refreshTable();
            }
        });

        actionLayout.getChildren().addAll(btnComplete, btnDelete);
        return actionLayout;
    }

    private void refreshTable() {
        table.getItems().clear();
        table.getItems().addAll(taskController.listAllTasks());
    }
}
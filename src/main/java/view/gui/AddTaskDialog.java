package view.gui;

import controller.TaskController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.TaskPriority;

import java.time.LocalDate;

public class AddTaskDialog extends Stage {

    public AddTaskDialog(TaskController controller, Runnable onTaskAdded) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Add New Task");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        TextField titleInput = new TextField();
        titleInput.setPromptText("Task Title");
        titleInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        TextField categoryInput = new TextField();
        categoryInput.setPromptText("Category");
        categoryInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        ComboBox<TaskPriority> priorityInput = new ComboBox<>();
        priorityInput.getItems().addAll(TaskPriority.values());
        priorityInput.setPromptText("Priority");
        priorityInput.setStyle("-fx-background-color: #2c3e50;");

        DatePicker deadlineInput = new DatePicker();
        deadlineInput.setPromptText("Deadline");
        deadlineInput.setStyle("-fx-control-inner-background: #2c3e50;");

        Button btnSave = new Button("Save Task");
        btnSave.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white;");
        btnSave.setOnAction(e -> {
            controller.addTasks(titleInput.getText(), "", priorityInput.getValue(), categoryInput.getText(), deadlineInput.getValue());
            onTaskAdded.run();
            this.close();
        });

        layout.getChildren().addAll(titleInput, categoryInput, priorityInput, deadlineInput, btnSave);

        this.setScene(new Scene(layout, 300, 350));
    }
}
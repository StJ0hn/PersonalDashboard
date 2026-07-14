package view.gui;

import controller.GoalController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddGoalDialog extends Stage {

    public AddGoalDialog(GoalController controller, Runnable onGoalAdded) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Add New Goal");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        TextField titleInput = new TextField();
        titleInput.setPromptText("Goal Title");
        titleInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        TextField areaInput = new TextField();
        areaInput.setPromptText("Area of Knowledge");
        areaInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        TextField hoursInput = new TextField();
        hoursInput.setPromptText("Target Hours (e.g. 100)");
        hoursInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        DatePicker deadlineInput = new DatePicker();
        deadlineInput.setPromptText("Deadline");
        deadlineInput.setStyle("-fx-control-inner-background: #2c3e50;");

        Button btnSave = new Button("Save Goal");
        btnSave.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        btnSave.setOnAction(e -> {
            if (!titleInput.getText().isEmpty() && !areaInput.getText().isEmpty() &&
                    !hoursInput.getText().isEmpty() && deadlineInput.getValue() != null) {
                try {
                    int targetHours = Integer.parseInt(hoursInput.getText());
                    // Verifica se o teu método na GoalController se chama addGoal ou addGoals
                    controller.addGoal(titleInput.getText(), areaInput.getText(), targetHours, deadlineInput.getValue());
                    onGoalAdded.run();
                    this.close();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid number format for hours.");
                }
            }
        });

        layout.getChildren().addAll(titleInput, areaInput, hoursInput, deadlineInput, btnSave);
        this.setScene(new Scene(layout, 300, 300));
    }
}
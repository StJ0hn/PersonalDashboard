package view.gui;

import controller.GoalController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddSessionDialog extends Stage {

    public AddSessionDialog(GoalController controller, String goalTitle, Runnable onSessionAdded) {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Log Study Time");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #1e1e1e;");

        Label contextLabel = new Label("Logging time for: " + goalTitle);
        contextLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #aaaaaa; -fx-font-weight: bold;");

        TextField minutesInput = new TextField();
        minutesInput.setPromptText("Minutes studied (e.g., 120)");
        minutesInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        TextField noteInput = new TextField();
        noteInput.setPromptText("What did you study? (Notes)");
        noteInput.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white;");

        Button btnSave = new Button("Save Session");
        btnSave.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        btnSave.setOnAction(e -> {
            if (!minutesInput.getText().isEmpty()) {
                try {
                    int minutes = Integer.parseInt(minutesInput.getText());
                    String note = noteInput.getText() != null ? noteInput.getText() : "";

                    // Ajusta o nome deste método para o que usaste no teu GoalController
                    controller.addStudySession(goalTitle, minutes, note);

                    onSessionAdded.run();
                    this.close();
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid number format for minutes.");
                }
            }
        });

        layout.getChildren().addAll(contextLabel, minutesInput, noteInput, btnSave);
        this.setScene(new Scene(layout, 300, 250));
    }
}
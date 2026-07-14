package view.gui;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import controller.GoalController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Goal;

public class GoalPanel {

    private GoalController goalController;
    private FlowPane cardsLayout;

    public GoalPanel(GoalController goalController) {
        this.goalController = goalController;
    }

    public VBox getView() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: #121212;");

        HBox header = new HBox(20);
        header.setAlignment(Pos.CENTER_LEFT);

        Label titleLabel = new Label("MANAGE GOALS");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");

        Button btnNewGoal = new Button("+ New Goal");
        btnNewGoal.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");
        btnNewGoal.setOnAction(e -> {
            AddGoalDialog dialog = new AddGoalDialog(goalController, this::refreshCards);
            dialog.showAndWait();
        });

        header.getChildren().addAll(titleLabel, btnNewGoal);

        cardsLayout = new FlowPane(20, 20); // Espaçamento horizontal e vertical entre os cartões
        cardsLayout.setStyle("-fx-background-color: #121212;");

        ScrollPane scrollPane = new ScrollPane(cardsLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #121212; -fx-border-color: #121212;");

        root.getChildren().addAll(header, scrollPane);
        refreshCards();

        return root;
    }

    private void refreshCards() {
        cardsLayout.getChildren().clear();
        // Verifica se o teu método se chama listAllGoals
        for (Goal goal : goalController.listAllGoals()) {
            cardsLayout.getChildren().add(createGoalCard(goal));
        }
    }

    private VBox createGoalCard(Goal goal) {
        VBox card = new VBox(15);
        card.setPadding(new Insets(20));
        card.setPrefSize(280, 180);
        card.setStyle("-fx-background-color: #1e1e1e; -fx-background-radius: 8px; -fx-border-color: #34495e; -fx-border-radius: 8px; -fx-border-width: 2px;");

        Label titleLabel = new Label(goal.getTitle());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: white; -fx-font-weight: bold;");

        Label areaLabel = new Label(goal.getAreaOfKnowledge());
        areaLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #aaaaaa;");

        // O JavaFX ProgressBar recebe valores de 0.0 a 1.0, por isso dividimos por 100
        // Calcula o progresso de 0.0 a 1.0 (agora blindado na tua model)
        double progress = goal.getProgressPercentage() / 100.0;

        // 1. Criamos o "trilho" vazio da barra (fundo cinza escuro)
        StackPane customProgressBar = new StackPane();
        customProgressBar.setAlignment(Pos.CENTER_LEFT);
        customProgressBar.setPrefSize(240, 16);
        customProgressBar.setMinHeight(16);
        customProgressBar.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 10px;");

        // 2. Criamos o preenchimento verde sólido
        Rectangle fill = new Rectangle();
        fill.setHeight(16);
        fill.setWidth(240 * progress); // A largura é matemática pura: total * percentagem
        fill.setFill(Color.web("#2ecc71"));
        fill.setArcWidth(10); // Arredonda os cantos
        fill.setArcHeight(10);

        customProgressBar.getChildren().add(fill);

        Label progressLabel = new Label(String.format("%.1f%% Completed", goal.getProgressPercentage()));
        progressLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #aaaaaa;");

        Button btnLogTime = new Button("Log Time");
        btnLogTime.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        btnLogTime.setOnAction(e -> {
            AddSessionDialog dialog = new AddSessionDialog(goalController, goal.getTitle(), this::refreshCards);
            dialog.showAndWait();
        });

        card.getChildren().addAll(titleLabel, areaLabel, customProgressBar, progressLabel, btnLogTime);
        return card;
    }
}
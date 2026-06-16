package view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainView {
    private Scanner scanner;
    private TaskView taskView;
    private GoalView goalView;

    public MainView() {
        this.scanner = new Scanner(System.in);
        this.taskView = new TaskView(this.scanner);
        this.goalView = new GoalView(this.scanner);
    }

    public int readIntegerNumber(String message){
        while (true){
            try {
                System.out.print(message);
                int number = Integer.parseInt(scanner.nextLine());
                return number;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Invalid data input. Try again.");
            }
        }
    }

    public void start() {
        while (true) {
            System.out.println("\n=== PERSONAL DASHBOARD ===");
            System.out.println("[1] MANAGE TASKS");
            System.out.println("[2] MANAGE STUDY GOALS");
            System.out.println("[0] END SYSTEM");

            int option = readIntegerNumber("Choice: ");

            switch (option) {
                case 1:
                    taskView.showTaskMenu();
                    break;
                case 2:
                    goalView.showGoalMenu();
                    break;
                case 0:
                    System.out.println("Ending System...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
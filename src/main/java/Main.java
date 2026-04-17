import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.Task;
import model.Goal;
import model.StudySession;
import model.TaskPriority;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Goal> goals = new ArrayList<>();

    public static void createTask() {
        //TODO Pedir dados pro usuário -> instanciar Task -> adicionar na lista 'tasks'
        System.out.print("Provide a title for the task: ");
        String title = sc.nextLine();
        System.out.print("Provide a description for the task: ");
        String description = sc.nextLine();
        System.out.print("Select priority [1 - High] [2 - Medium] [3 - Low]: ");
        TaskPriority priority;
        while (true){
            int optionPriority = Integer.parseInt(sc.nextLine());
            if (optionPriority == 1){
                priority = TaskPriority.HIGH;
                break;
            } else if (optionPriority == 2) {
                priority = TaskPriority.MEDIUM;
                break;
            } else if (optionPriority == 3) {
                priority = TaskPriority.LOW;
                break;
            }
            else {
                System.out.print("Invalid option. Try again.");
                continue;
            }
        }
        System.out.print("Provide a category for the task: ");
        String category = sc.nextLine();
        System.out.print("Enter the task expiration date (yyyy-MM-dd): ");
        String input = sc.nextLine();
        LocalDate dueDate = LocalDate.parse(input);
        Task newTask = new Task(title, description, priority, category, dueDate);
        tasks.add(newTask);
        System.out.println("Task added successfully!");
    }

    public static void createGoal() {
        //TODO Pedir dados pro usuário -> instanciar Goal -> adicionar na lista 'goals'
    }

    public static void listAll() {
        //TODO Fazer um for-each na lista de tasks e imprimir
        //TODO Fazer um for-each na lista de goals e imprimir (aqui você já pode chamar o getProgressPercentage() para testar!)
    }

    public static void completeTask() {
        //TODO Pedir o título -> buscar na lista 'tasks' -> se achar, chamar .markAsCompleted()
    }

    public static void registerStudySession() {
        //TODO Implementação do UC09 (Veja a dica abaixo)
    }

    // 3. O Loop Principal
    public static void main(String[] args) {
        while (true) {
            System.out.println("=== PERSONAL DASHBOARD ===");
            System.out.println("[1] Create Task");
            System.out.println("[2] Create Goal");
            System.out.println("[3] Registrar Sessão de Estudo");
            System.out.println("[4] Listar Tudo");
            System.out.println("[5] Concluir Tarefa");
            System.out.println("[0] Sair");

            //TODO Ler escolha e fazer os IFs/Switch para chamar os métodos acima
            System.out.print("Select Option: ");
            int option = Integer.parseInt(sc.nextLine());
            switch (option){
                case 1:
                    createTask();
                    break;
            }
        }
    }
}
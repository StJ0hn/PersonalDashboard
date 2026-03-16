import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] titles = new String[50];
        String[] priorities = new String[50];
        boolean[] completed = new boolean[50];
        int taskCount = 0;
        while (true){
            System.out.println(" [1] - Create task\n [2] - List tasks\n [0] - Exit\n Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1){
                System.out.println("Title: ");
                String title = sc.nextLine();
                while (title.isBlank()){
                    System.out.println("Please, type a title for the task: ");
                    title = sc.nextLine();
                }
                System.out.println("Priority(High/Medium/Low)");
                String priority = sc.nextLine();
                titles[taskCount] = title;
                priorities[taskCount] = priority;
                completed[taskCount] = false;
                System.out.println("Task created!");
                taskCount++;
            } else if (choice == 2) {
                for (int i = 0; i < taskCount; i++) {
                    String status = completed[i] ? "Completed" : "Pending";
                    System.out.println("Task -> Title: " + titles[i] + " | Priority: " + priorities[i] + " | Status: " + status);
                }
            } else if (choice == 0) {
                System.out.println("Leaving...");
                break;
            }
        }
    }
}

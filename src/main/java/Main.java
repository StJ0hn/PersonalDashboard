import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static String[] titles = new String[50];
    static String[] priorities = new String[50];
    static boolean[] completed = new boolean[50];
    static int taskCount = 0;
    public static void createTask(){
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
        taskCount++;
    }

    public static void listTasks(){
        for (int i = 0; i < taskCount; i++) {
            String status = completed[i] ? "Completed" : "Pending";
            System.out.println("Task -> Title: " + titles[i] + " | Priority: " + priorities[i] + " | Status: " + status);
        }
    }

    public static void setCompletedTask(){
        System.out.println("Give the title of task: ");
        String titleTaskSearch = sc.nextLine();
        boolean taskFounded = false;
        for (int task = 0; task < taskCount; task++) {
            if (titleTaskSearch.equals(titles[task])){
                completed[task] = true;
                taskFounded = true;
            }
        }
        if (!taskFounded){
            System.out.println("Task not found! Please try again!");
        }
    }

    public static void main(String[] args) {
        while (true){
            System.out.println(" [1] - Create task\n [2] - List tasks\n [3] - Mark task as completed \n [0] - Exit\n Choice: ");
            int choice = Integer.parseInt(sc.nextLine());
            if (choice == 1){
                createTask();
                System.out.println("Task Created!");
            } else if (choice == 2) {
                listTasks();
            } else if (choice == 3) {
                setCompletedTask();
            } else if (choice == 0) {
                System.out.println("Leaving...");
                break;
            }
        }
    }
}

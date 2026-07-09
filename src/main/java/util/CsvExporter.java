package util;

import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvExporter {
    public static void exportTasksToCSV(List<Task> tasks, String fileName) {
        if (!fileName.endsWith(".csv")) {
            fileName += ".csv";
        }

        java.io.File directory = new java.io.File("exports");
        if (!directory.exists()) {
            directory.mkdir();
        }

        String fullPath = "exports/" + fileName;

        try (PrintWriter writer = new PrintWriter(new FileWriter(fullPath))) {
            writer.println("Title;Description;Category;Priority;DueDate;Status");
            for (Task task : tasks) {
                String line = task.getTitle() + ";" +
                        task.getDescription() + ";" +
                        task.getCategory() + ";" +
                        task.getTaskPriority() + ";" +
                        task.getDueDate() + ";" +
                        task.getTaskStatus();

                writer.println(line);
            }

        } catch (IOException e) {
            System.out.println("Error exporting to CSV: " + e.getMessage());
        }
    }
}

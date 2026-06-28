package util;

import model.TaskPriority;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputUtils {
    public static int readIntegerNumber(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid data input. Please, enter a valid number.");
            }
        }
    }


    public static String readRequiredString (Scanner scanner, String message){
        while (true){
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }


    public static LocalDate readFutureDate(Scanner scanner, String message){
        while (true){
            try {
                System.out.print(message);
                LocalDate date = LocalDate.parse(scanner.nextLine().trim());
                if (date.isBefore(LocalDate.now())){
                    System.out.println("The date cannot be in the past. Try again.");
                    continue;
                }
                return date;
            }
            catch (DateTimeParseException dateTimeParseException){
                System.out.println("Invalid date format. Try again with 'yyyy-MM-dd' format.");
            }
        }
    }

    public static TaskPriority readTaskPriority(Scanner scanner, String message){
        while (true){
            int optionPriority = readIntegerNumber(scanner, message);
            TaskPriority priority = null;
            while (priority == null) {
                if (optionPriority == 1) {
                    return TaskPriority.HIGH;
                }
                else if (optionPriority == 2) {
                    return TaskPriority.MEDIUM;
                }
                else if (optionPriority == 3) {
                    return TaskPriority.LOW;
                }
                else {
                    System.out.print("Invalid option. Try again: ");
                }
            }
        }
    }
}

package util;

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
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()){
                return input;
            }
            System.out.println("This field cannot be empty. Please try again.");
        }
    }

    public static LocalDate readDate(Scanner scanner, String message) {
        while (true){
            try {
                System.out.println(message);
                LocalDate date = LocalDate.parse(scanner.nextLine());
                return date;
            }
            catch (DateTimeParseException dateTimeParseException){
                System.out.println("Invalid date format. Try again with 'yyyy-mm-dd' format.");
            }
        }
    }
}

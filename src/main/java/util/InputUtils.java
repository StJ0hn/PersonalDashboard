package util;

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
}

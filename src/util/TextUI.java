package util;

import java.util.Scanner;

public class TextUI {

    Scanner sc = new Scanner(System.in);

    public int promptNumeric() {
        int numInput = 0;
        boolean valid = false;

        while (!valid) {
            String input = sc.nextLine();  // Get user input
            try {
                numInput = Integer.parseInt(input);  // Try converting to number
                valid = true;  // If successful, mark as valid
            } catch (NumberFormatException e) {
                displayMessage("Skriv et gyldigt tal.");  // Error message, loop repeats
            }
        }
        return numInput;  // Return the valid number
    }

    public void displayMessage(String msg) {

        System.out.println(msg);
    }
}

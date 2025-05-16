package util;

import java.util.Scanner;

public class TextUI {

    Scanner scan = new Scanner(System.in);

    public int promptNumeric() {
        int input = 0;

        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException e) {

            }
        }
        return input;
    }

    public int promptNumeric(String msg) {
        int input = 0;
        System.out.println(msg + " ");
        while (true) {
            try {
                input = Integer.parseInt(scan.nextLine());
                break;
            } catch (NumberFormatException e) {

            }
        }
        return input;
    }

    public void displayMessage(String msg) {

        System.out.println(msg);
    }
}

package training.busboard;

import java.util.Scanner;

public class Utilities {

    public static void welcomeMsg(String msg) {
        System.out.println(msg);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            s.append("=");
        }
        System.out.println(s.toString());
    }

    public static int requestInt(String msg) {
        while (true) {
            String requestNumber = requestString(msg);
            try {
                return Integer.valueOf(requestNumber);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    public static String requestString(String msg) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(msg);
        System.out.print(">");
        return scanner.nextLine();
    }

}

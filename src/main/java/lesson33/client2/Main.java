package lesson33.client2;

import java.util.Scanner;

public class Main {
    static Scanner scanner;
    static String user;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.println("Start to chat ? yes/no");
        String answer = scanner.nextLine();
        if (answer.equals("yes")) {
            System.out.println("Please enter your username: ");
            if(scanner.hasNext()){
                user = scanner.next();
                System.out.println("Hi! type your sms or \"exit\" to quit");
                ChatClient client = new ChatClient();
                client.start(scanner, user);
            } else if (answer.equals("no")) {
                System.out.println("bye-bye");
            }else {
                System.out.println("Unknown command");
            }
        }
    }
}

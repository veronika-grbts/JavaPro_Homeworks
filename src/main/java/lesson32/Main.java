package lesson32;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CoffeeOrderBoard coffeeOrderBoard = new CoffeeOrderBoard();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nDatabase Menu");
            System.out.println("1. Add Coffee");
            System.out.println("2. Display Coffee Queue");
            System.out.println("3. Delete Coffee (Next in Queue)");
            System.out.println("4. Delete Coffee (By Order Number)");
            System.out.println("0. Exit");
            System.out.println("Please choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    Order order = new Order(customerName);
                    coffeeOrderBoard.add(order);
                    break;
                case 2:
                    coffeeOrderBoard.draw();
                    break;
                case 3:
                    Order nextOrder = coffeeOrderBoard.deliver();
                    if (nextOrder != null) {
                        System.out.println("Delivered order: " + nextOrder);
                    }
                    break;
                case 4:
                    System.out.println("Enter order number to delete: ");
                    int orderNumber = scanner.nextInt();
                    scanner.nextLine();

                    Order specificOrder = coffeeOrderBoard.deliver(orderNumber);
                    if (specificOrder != null) {
                        System.out.println("Delivered order: " + specificOrder);
                    }
                    break;
                case 0:
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        HibernateUtil.shutDown();
    }
}

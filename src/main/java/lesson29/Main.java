package lesson29;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DatabaseConnector.initializeDatabase();
        EmployeeDAO employeeDAO = new EmployeeDAO();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nDatabase Menu");
            System.out.println("1. Add employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("0. Exit");
            System.out.println("Please choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.println("Enter salary: ");
                    float salary = scanner.nextFloat();
                    scanner.nextLine();
                    employeeDAO.addEmployees(new Employees(0, name, age, position, salary));
                    break;
                case 2:
                    employeeDAO.viewEmployees();
                    break;
                case 3:
                    System.out.println("Enter employee ID to change: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.println("Enter new age: ");
                    int newAge = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    System.out.println("Enter new salary: ");
                    float newSalary = scanner.nextFloat();
                    scanner.nextLine();

                    employeeDAO.updateEmployees(new Employees(updateId, newName, newAge, newPosition, newSalary));
                    break;
                case 4:
                    System.out.println("Enter employee ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    employeeDAO.deleteEmployees(deleteId);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }

    }
}
package lesson30;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        StudentDAO studentDAO = new StudentDAO();
        HomeworkDAO homeworkDAO = new HomeworkDAO();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nDatabase Menu");
            System.out.println("1. Add student");
            System.out.println("2. View All student");
            System.out.println("3. View student by ID");
            System.out.println("4. View student by email");
            System.out.println("5. Update student");
            System.out.println("6. Delete student");
            System.out.println("7. Add homework");
            System.out.println("0. Exit");
            System.out.println("Please choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.println("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.println("Enter email: ");
                    String email = scanner.nextLine();

                    Student student = new Student(firstName, lastName, email);
                    studentDAO.save(student);
                    break;
                case 2:
                    studentDAO.findAll().forEach(System.out::println);
                    break;
                case 3:
                    System.out.println("Enter id: ");
                    long id = scanner.nextInt();
                    Student userById = studentDAO.findById(id);
                    System.out.println(userById);
                    break;
                case 4:
                    System.out.println("Enter email: ");
                    String findEmail = scanner.nextLine();
                    studentDAO.findByEmail(findEmail).forEach(System.out::println);
                    break;
                case 5:
                    System.out.println("Enter student ID to change: ");
                    long updateId = scanner.nextLong();
                    scanner.nextLine();
                    Student existingStudent = studentDAO.findById(updateId);
                    if (existingStudent != null) {
                        System.out.println("Enter new first name");
                        String newFirstName = scanner.nextLine();
                        System.out.println("Enter new last name");
                        String newLastName = scanner.nextLine();
                        System.out.println("Enter new email");
                        String newEmail = scanner.nextLine();
                        existingStudent.setFirstName(newFirstName);
                        existingStudent.setLastName(newLastName);
                        existingStudent.setEmail(newEmail);
                        studentDAO.update(existingStudent);
                        System.out.println("Student updated successfully!");
                    } else {
                        System.out.println("Student with ID " + updateId + " not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter user ID to delete:");
                    long deleteUserID = scanner.nextInt();
                    scanner.nextLine();
                    studentDAO.deleteById(deleteUserID);
                    break;
                case 7:
                    System.out.println("Enter student ID to assign homework: ");
                    long studentId = scanner.nextLong();
                    scanner.nextLine();
                    Student studentForHomework = studentDAO.findById(studentId);
                    if (studentForHomework != null) {
                        System.out.println("Enter homework description: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter deadline (yyyy-MM-dd): ");
                        LocalDate deadline = LocalDate.parse(scanner.nextLine());
                        System.out.println("Enter mark: ");
                        int mark = scanner.nextInt();

                        Homework homework = new Homework();
                        homework.setDescription(description);
                        homework.setDeadline(deadline);
                        homework.setMark(mark);
                        homework.setStudent(studentForHomework);

                        studentForHomework.addHomework(homework);
                        homeworkDAO.save(homework);
                        System.out.println("Homework added successfully!");
                    } else {
                        System.out.println("Student with ID " + studentId + " not found.");
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }


        HibernateUtil.shutDown();
    }
}

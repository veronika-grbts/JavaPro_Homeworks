package lesson18;

import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        Optional<User> userId = userRepository.findUserById(22);
        userId.ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));

        Optional<User> userEmail = userRepository.findUserByEmail("max@gmail.com");
        userEmail.ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));

        Optional<List<User>> listUsers = userRepository.findAllUsers();
        listUsers.ifPresentOrElse(System.out::println, () -> System.out.println("User not found"));

        userRepository.userCount();
    }
}

package lesson18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private static final List<User> users = Arrays.asList(
            new User(11, "Max", "max@gmail.com"),
            new User(22, "Alex", "alex@gmail.com"),
            new User(33, "Oleg", "oleg@gmail.com"),
            new User(23, "Bob", "bob@gmail.com")
    );

    public Optional<User> findUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findAny();
    }

    public Optional<User> findUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();

    }

    public Optional<List<User>> findAllUsers() {
        return Optional.ofNullable(users);
    }

    public void userCount() {
        findAllUsers().ifPresentOrElse(
                usersList -> System.out.println("count User in Optional<List<User>>  " + usersList.size()),
                () -> System.out.println("List is empty")
        );
    }

}

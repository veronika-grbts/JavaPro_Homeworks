package lessons.lesson31;


import lesson31.livecode.User;
import lesson31.livecode.UserDAO;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    private static Connection connection;
    private static UserDAO userDAO;
    private User tempUser;
    @BeforeAll
    public static void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/livecode", "postgres", "admin");
        userDAO = new UserDAO(connection);
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "email VARCHAR(255) UNIQUE);");
        }
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        try(Statement statement = connection.createStatement()){
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        }
        connection.close();
    }

    @BeforeEach
    void beforeEach() throws SQLException {
        tempUser = userDAO.save(new User("Test User", "temp@gmail.com"));
    }

    @AfterEach
    void afterEach() throws SQLException {
        userDAO.delete(tempUser.getId());
    }

    @Test
    @Order(1)
    public void testCreateUser() throws SQLException {
        User nika = new User("Nika", "nika@gmail.com");
        User saveUser = userDAO.save(nika);
        assertNotNull(saveUser.getId());
    }

    @Test
    @Order(2)
    public void testInvalidUserCreation() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userDAO.save(new User(null, "invalid@gmail.com"));
        });
        assertEquals("Invalid User", illegalArgumentException.getMessage());

    }

    @Test
    @Order(3)
    void findByEmailTest() throws SQLException {
        Optional<User> byEmail = userDAO.findByEmail(tempUser.getEmail());
        assertTrue(byEmail.isPresent());
        assertEquals(tempUser.getName(), byEmail.get().getName());

    }

    @Test
    @Order(4)
    void findByEmailExceptionTest(){
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userDAO.findByEmail(null);
        });
        assertEquals("Invalid Email or Blank Email", illegalArgumentException.getMessage());

        illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userDAO.findByEmail("");
        });
        assertEquals("Invalid Email or Blank Email", illegalArgumentException.getMessage());
    }

    @Test
    @Order(5)
    void allUsersTest() throws SQLException {
        List<User> allUsers = userDAO.findAll();
        assertFalse(allUsers.isEmpty());
    }

    @Test
    @Order(6)
    void deleteUserTest() throws SQLException {
        Optional<User> user = userDAO.findByEmail(tempUser.getEmail());
        user.ifPresent(user1 ->{
            try {
                userDAO.delete(user1.getId());
            } catch (SQLException e) {
                System.out.println("DELETE FAILED");
            }
        });
        Optional<User> user2 = userDAO.findByEmail(tempUser.getEmail());
        assertFalse(user2.isPresent());
    }

    @Test
    @Order(7)
    void deleteUserExceptionTest() throws SQLException {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            userDAO.delete(null);
        });
        assertEquals("Invalid ID", illegalArgumentException.getMessage());
    }

    @Test
    void  timeOutTest(){
        assertTimeout(Duration.ofSeconds(1), () -> {
            List<User> all = userDAO.findAll();
            assertFalse(all.isEmpty());
        });
    }


    @ParameterizedTest
    @ValueSource(strings = {"alex@gmail.com", "nik@gmail.com", "bob@gmail.com"})
    void testParametrized(String email) throws SQLException {
        User user = new User(email.split("@")[0], email);
        userDAO.save(user);
        Optional<User> fetchUser = userDAO.findByEmail(email);
        assertTrue(fetchUser.isPresent());
      /*  assertEquals(email, fetchUser.get().getEmail());*/
    }
}

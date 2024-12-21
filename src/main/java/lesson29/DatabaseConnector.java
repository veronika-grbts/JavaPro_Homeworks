package lesson29;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    public static void initializeDatabase() {

        try(Connection connection = DriverManager.getConnection(DBProperties.URL, DBProperties.USER, DBProperties.PASSWORD);
        Statement statement = connection.createStatement()) {
            Class.forName("org.postgresql.Driver");

            String createEmployeesTableSQL = "CREATE TABLE IF NOT EXISTS employees(" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(100)," +
                    "age INTEGER, " +
                    "position VARCHAR(100),"+
                    "salary FLOAT);";
            statement.execute(createEmployeesTableSQL);
            System.out.println("Database initialized.");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DBProperties.URL, DBProperties.USER, DBProperties.PASSWORD);
    }
}

package lesson29;

import java.sql.*;


public class EmployeeDAO {

    public void addEmployees(Employees employees) {
        String insertEmployeesSQL = "INSERT INTO employees(name, age, position, salary) VALUES (?, ?, ?, ?);";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertEmployeesSQL)) {
            preparedStatement.setString(1, employees.getName());
            preparedStatement.setInt(2, employees.getAge());
            preparedStatement.setString(3, employees.getPosition());
            preparedStatement.setDouble(4, employees.getSalary());
            preparedStatement.executeUpdate();

            System.out.println("Employees added successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployees(Employees employees) {
        String updateEmployeesSQL = "UPDATE employees SET name = ?, age = ?, position = ?, salary = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateEmployeesSQL)) {
            preparedStatement.setString(1, employees.getName());
            preparedStatement.setInt(2, employees.getAge());
            preparedStatement.setString(3, employees.getPosition());
            preparedStatement.setDouble(4, employees.getSalary());
            preparedStatement.setInt(5, employees.getId());
            int countRow = preparedStatement.executeUpdate();
            System.out.println("Rows updated: " + countRow);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteEmployees(int id) {
        String deleteEmployeesSQL = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteEmployeesSQL)) {

            preparedStatement.setInt(1, id);
            int countRow = preparedStatement.executeUpdate();
            System.out.println("Rows delete: " + countRow);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewEmployees() {
        String viewEmployeesSQL = "SELECT * FROM employees";

        try (Connection connection = DatabaseConnector.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(viewEmployeesSQL);
            while (resultSet.next()) {
                Employees employees = new Employees(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("position"),
                        resultSet.getInt("salary")
                );

                System.out.println(employees);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

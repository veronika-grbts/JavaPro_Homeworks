package lesson31.livecode;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    private final Connection conn;

    public UserDAO(Connection conn) {
        this.conn = conn;
    }

    public User save(User user) throws SQLException {
        if(user == null || !user.isValid()){
            throw new IllegalArgumentException("Invalid User");
        }

        String q =  "insert into users (name, email) values(?,?) RETURNING id";

        try(PreparedStatement preparedStatement = conn.prepareStatement(q)){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                user.setId(rs.getLong("id"));
            }
        }
        return user;
    }

    public Optional<User> findByEmail(String email) throws SQLException {
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("Invalid Email or Blank Email");
        }

        String q =  "select * from users where email = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(q)){
            preparedStatement.setString(1, email);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
              return Optional.of(
                      new User(rs.getLong("id"),
                              rs.getString("name"),
                              rs.getString("email")));
            }
        }
        return Optional.empty();
    }

    public List<User> findAll() throws SQLException {
        String q =  "select * from users";
        List<User> users = new ArrayList<>();
        try(Statement statement = conn.createStatement()){
           ResultSet resultSet = statement.executeQuery(q);


           if(resultSet.next()){
               users.add(new User(resultSet.getLong("id"),
                       resultSet.getString("name"),
                       resultSet.getString("email")));
           }
        }
        return users;
    }

    public void delete(Long id) throws SQLException {
        if(id == null){
            throw new IllegalArgumentException("Invalid ID");
        }

        String q =  "delete from users where id = ?";

        try(PreparedStatement preparedStatement = conn.prepareStatement(q)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}

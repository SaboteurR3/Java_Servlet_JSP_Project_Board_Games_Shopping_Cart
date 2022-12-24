package ge.tsu.shoppingcartproject.dao;

import ge.tsu.shoppingcartproject.model.Order;
import ge.tsu.shoppingcartproject.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User userLogin(String email, String password) {
        User user = null;
        String query;
        try {
            query = "select * from users where email=? and password=?";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            result = statement.executeQuery();
            if(result.next()){
                user = new User();
                user.setId(result.getInt("id"));
                user.setName(result.getString("name"));
                user.setEmail(result.getString("email"));
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
        return user;
    }
    public boolean userRegister(String name, String email, String password) {
        User user = null;
        String query;
        boolean result = false;
        try {
            query = "insert into users (name, email, password) values(?,?,?)";
            statement = this.connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}

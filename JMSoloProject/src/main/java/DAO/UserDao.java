package DAO;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    boolean addUser(String name, String email, String phone, String role) throws SQLException;

    boolean hasUser(String name, String email) throws SQLException;

    void deleteUser(long id) throws SQLException;

    void updateUser(User user) throws SQLException;

    User getUserById(long id) throws SQLException;

    User getUser (String name, String email) throws SQLException;

    List<User> getAllUsers() throws SQLException;
}

package DAO;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCimpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(String name, String email, String phone, String role) throws SQLException {
        if (!hasUser(name, email)) {
            final String queryCheck = "INSERT INTO  users_info (name, email, phone, role) VALUES\n" +
                    "(?, ?, ?, ?)";
            final PreparedStatement result = connection.prepareStatement(queryCheck);
            result.setString(1, name);
            result.setString(2, email);
            result.setString(3, phone);
            result.setString(4, role);
            result.executeUpdate();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUser(String name, String email) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users_info " +
                "WHERE name=? AND email=?;");
        ps.setString(1, name);
        ps.setString(2, email);
        ResultSet rs = ps.executeQuery();
        boolean result = (rs.next()) ? true : false;
        rs.close();
        return result;
    }

    @Override
    public void deleteUser(long id) throws SQLException {
        final String queryCheck = "DELETE FROM users_info WHERE id=?";
        final PreparedStatement result = connection.prepareStatement(queryCheck);
        result.setLong(1, id);
        result.executeUpdate();
    }

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("UPDATE users_info SET name=?, email=?, phone=?, role=? " +
                "WHERE id=?;");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getPhone());
        ps.setString(4, user.getRole());
        ps.setLong(5, user.getId());
        ps.executeUpdate();
    }

    @Override
    public User getUserById(long id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users_info WHERE id=?");
        ps.setLong(1, id);
        ResultSet result = ps.executeQuery();
        result.next();
        Long userId = result.getLong(1);
        String userName = result.getString(2);
        String userEmail = result.getString(3);
        String userPhone = result.getString(4);
        String userRole = result.getString(5);
        ps.close();
        return new User(userId, userName, userEmail, userPhone, userRole);
    }

    @Override
    public User getUser(String name, String email) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM users_info WHERE name=? AND email=?");
        ps.setString(1, name);
        ps.setString(2, email);
        ResultSet result = ps.executeQuery();
        result.next();
        Long userId = result.getLong(1);
        String userName = result.getString(2);
        String userEmail = result.getString(3);
        String userPhone = result.getString(4);
        String userRole = result.getString(5);
        ps.close();
        return new User(userId, userName, userEmail, userPhone, userRole);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        java.sql.Statement stm = connection.createStatement();
        String sql = "SELECT * FROM users_info";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        while (rst.next()) {
            User user = new User(rst.getLong("id"),
                    rst.getString("name"), rst.getString("email"),
                    rst.getString("phone"), rst.getString("role"));
            users.add(user);
        }
        stm.close();
        return users;
    }


    public void createTable() throws SQLException {
        java.sql.Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists users_info (id bigint auto_increment, name varchar(256), \n" +
                "email varchar(256), phone varchar(256), role varchar(256), primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        java.sql.Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users_info");
        stmt.close();
    }
}

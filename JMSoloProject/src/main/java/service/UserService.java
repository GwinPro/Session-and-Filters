package service;

import DAO.UserDao;
import DAO.UserDaoFactory;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static UserService userService;

    private UserDao userDao() {
        return UserDaoFactory.getUserDaoFactory().getUserDAO();
    }


    private UserService() {
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


    public List<User> getAllClient() {
        List<User> allClients = new ArrayList<>();
        try {
            allClients = userDao().getAllUsers();
        } catch (SQLException e) {

        }
        return allClients;
    }

    public boolean addUser(String name, String email, String phone, String role) {
        boolean result = false;
        try {
            result = userDao().addUser(name, email, phone, role);
        } catch (SQLException e) {
        }
        return result;
    }

    public boolean hasUser(String name, String email) {
        boolean result = false;
        if (name != "" && email != "") {
            try {
                result = userDao().hasUser(name, email);
            } catch (SQLException e) {
            }
        }
        return result;
    }

    public void deleteUser(Long id) {
        try {
            userDao().deleteUser(id);
        } catch (SQLException e) {
        }
    }

    public boolean updateUser(User user) {
        boolean result = false;
        try {
            userDao().updateUser(user);
            result = true;
        } catch (SQLException e) {
        }
        return result;
    }

    public User getUserById(Long id) {
        User user = null;
        try {
            user = userDao().getUserById(id);
        } catch (SQLException e) {
        }
        return user;
    }

    public User getUser(String name, String email) {
        User user = null;
        try {
            user = userDao().getUser(name, email);
        } catch (SQLException e) {
        }
        return user;
    }


}

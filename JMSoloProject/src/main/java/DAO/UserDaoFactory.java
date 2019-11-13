package DAO;

import org.hibernate.SessionFactory;
import util.DBHelper;
import util.PropertyReader;

import java.sql.Connection;

public class UserDaoFactory {
    private static Connection connection = DBHelper.getDbHelper().getConnection();
    private static SessionFactory factory = DBHelper.getDbHelper().getConfiguration();
    private static UserDaoFactory userDaoFactory;

    private UserDaoFactory() {
    }

    public static UserDaoFactory getUserDaoFactory() {
        if (userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    public UserDao getUserDAO() {

        String getMyDao = PropertyReader.getProperty("SetDAO");
        //getMyDao="JDBC";
        if (getMyDao.contains("JDBC")) {
            return new UserDaoJDBCimpl(connection);
        } else if (getMyDao.contains("hibernate")) {
            return new UserDaoHibernateImpl(factory.openSession());
        }
        return new UserDaoHibernateImpl(factory.openSession());
    }

}


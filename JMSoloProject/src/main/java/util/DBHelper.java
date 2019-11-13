package util;


import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {
    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getDbHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("solotaskone?").          //db name
                    append("user=root&").          //login
                    append("password=2810").       //password
                    append("&serverTimezone=UTC");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public SessionFactory getConfiguration() {
        Configuration configuration = createConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @SuppressWarnings("UnusedDeclaration")
    private static Configuration createConfiguration() {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty("hibernate.dialect", PropertyReader.getProperty("dialect"))
                .setProperty("hibernate.connection.driver_class", PropertyReader.getProperty("driver"))
                .setProperty("hibernate.connection.url", PropertyReader.getProperty("url"))
                .setProperty("hibernate.connection.username", PropertyReader.getProperty("username"))
                .setProperty("hibernate.connection.password", PropertyReader.getProperty("password"))
                .setProperty("hibernate.show_sql", PropertyReader.getProperty("show_sql"))
                .setProperty("hibernate.hbm2ddl", PropertyReader.getProperty("hbm2ddl"));
        return configuration;
    }


}

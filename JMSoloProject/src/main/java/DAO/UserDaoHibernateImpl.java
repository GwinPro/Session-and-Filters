package DAO;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private Session session;

    public UserDaoHibernateImpl(Session session) {
        this.session = session;
    }


    @Override
    public boolean addUser(String name, String email, String phone, String role) {
        if (!hasUser(name,email)) {
            User user = new User(name, email, phone, role);
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUser(String name, String email) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(" SELECT COUNT (*) FROM User user  WHERE user.name = :name " +
                "AND user.email = :email");
        query.setParameter("name", name);
        query.setParameter("email", email);
        Long rows = (Long) query.uniqueResult();
        transaction.commit();
        if (rows != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("DELETE FROM User user WHERE user.id = :id ");
        query.setParameter("id", id);
        int rows = query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(User user){
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public User getUserById(long id) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User user WHERE user.id = :id ");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public User getUser(String name, String email) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User user WHERE user.name = :name AND user.email = :email");
        query.setParameter("name", name);
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User user").list();
        transaction.commit();
        session.close();
        return users;
    }


}

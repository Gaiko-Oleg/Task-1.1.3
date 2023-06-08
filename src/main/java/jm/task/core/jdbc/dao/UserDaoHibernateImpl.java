package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.getTransaction();
            tx.begin();
            String sql = "CREATE TABLE IF NOT EXISTS user1 " +
                    "(id SERIAL PRIMARY KEY, name VARCHAR(20)," +
                    " lastName VARCHAR(20), age INTEGER)";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.getTransaction();
            tx.begin();
            String sql = "DROP TABLE IF EXISTS user1";
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.getTransaction();
            tx.begin();
            User user = new User(name, lastName, age);
            session.save(user);
           tx.commit();
         } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.getTransaction();
            tx.begin();
            User user = (User) session.load(User.class, id);
            session.delete(user);
            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();

        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.getTransaction();
            tx.begin();
            String sql = "SELECT * FROM user1";
            Query<User> query = session.createQuery("FROM User", User.class);
            users = query.list();
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction tx = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();

        }
    }
}

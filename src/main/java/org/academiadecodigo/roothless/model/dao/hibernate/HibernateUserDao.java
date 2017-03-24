package org.academiadecodigo.roothless.model.dao.hibernate;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.persistence.TransactionException;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public class HibernateUserDao implements UserDao {

    Session session;

    @Override
    public void create(User user) {
        try {
            session = HibernateSessionManager.getSession();
            session.save(user);
        } catch (HibernateException hibernateException) {
            throw new TransactionException(hibernateException);
        }

    }

    @Override
    public User read(String name) {

        User user = null;
        try {
            session = HibernateSessionManager.getSession();

            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("username", name))
                    .uniqueResult();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }

        return user;
    }

    @Override
    public void update(User user) {
        try {
            session = HibernateSessionManager.getSession();
            session.merge(user);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(User user) {

        try {
            session = HibernateSessionManager.getSession();
            session.delete(user);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }

    }

    @Override
    public int listSize() {

        int size;
        try {
            session = HibernateSessionManager.getSession();
            size = ((Long) session.createQuery("select count(*) from User").uniqueResult()).intValue();
        } catch (HibernateException hex) {
            throw new TransactionException(hex);
        }
        return size;
    }

    @Override
    public User findById(int id) {
        User user;
        try {
            session = HibernateSessionManager.getSession();

            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("id", id))
                    .uniqueResult();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }

        return user;
    }


}// End

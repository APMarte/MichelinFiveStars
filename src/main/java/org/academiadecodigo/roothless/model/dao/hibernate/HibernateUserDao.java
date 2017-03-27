package org.academiadecodigo.roothless.model.dao.hibernate;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.AbstractDao;
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
public class HibernateUserDao extends AbstractDao <User> implements UserDao {

    Session session;

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

//    public User read(String name) {
//
//        User user = null;
//        try {
//            session = HibernateSessionManager.getSession();
//
//            user = (User) session.createCriteria(User.class)
//                    .add(Restrictions.eq("username", name))
//                    .uniqueResult();
//
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//
//        return user;
//    }
//
//
//
//    @Override
//    public int listSize() {
//
//        int size;
//        try {
//            session = HibernateSessionManager.getSession();
//            size = ((Long) session.createQuery("select count(*) from User").uniqueResult()).intValue();
//        } catch (HibernateException hex) {
//            throw new TransactionException(hex);
//        }
//        return size;
//    }
//
//    @Override
//    public User findById(int id) {
//        User user;
//        try {
//            session = HibernateSessionManager.getSession();
//
//            user = (User) session.createCriteria(User.class)
//                    .add(Restrictions.eq("id", id))
//                    .uniqueResult();
//
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//
//        return user;
//    }
//

}// End

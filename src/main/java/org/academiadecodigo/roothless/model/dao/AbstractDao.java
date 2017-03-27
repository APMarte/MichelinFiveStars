package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.persistence.TransactionException;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by codecadet on 24/03/17.
 */
public class AbstractDao<T> implements Dao<T> {

    Session session;



    @Override
    public void create(T t) {
        try {
            session = HibernateSessionManager.getSession();
            session.saveOrUpdate(t);
        } catch (HibernateException hibernateException) {
            throw new TransactionException(hibernateException);
        }
    }


    @Override
    public T findById(Long id) {
        try {
            session = HibernateSessionManager.getSession();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void update(T t) {
        try {
            session = HibernateSessionManager.getSession();
            session.merge(t);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }
    }

    @Override
    public void delete(T t) {
        try {
            session = HibernateSessionManager.getSession();
            session.delete(t);

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }

    }
} // End

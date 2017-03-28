package org.academiadecodigo.roothless.persistence.hibernate;

import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.hibernate.Session;

/**
 * Created by codecadet on 23/03/17.
 */
public class HibernateTransactionManager implements TransactionManager {

    private Session session;
    HibernateSessionManager sessionManager;

    @Override
    public void begin() {
        HibernateSessionManager.beginTransaction();
    }

    @Override
    public void commit() {
        HibernateSessionManager.commitTransaction();
    }

    @Override
    public void rollback() {
        HibernateSessionManager.rollbackTransaction();
    }

    public HibernateSessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(HibernateSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
}



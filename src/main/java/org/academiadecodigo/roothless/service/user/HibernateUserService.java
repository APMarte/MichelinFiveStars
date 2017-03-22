package org.academiadecodigo.roothless.service.user;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.persistence.ConnectionManager;
import org.academiadecodigo.roothless.persistence.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.*;
import java.util.List;

/**
 * Created by codecadet on 13/03/17.
 */
public class HibernateUserService implements UserService {


    Session session;

    @Override
    public boolean authenticate(String name, String password) {

        session = HibernateSessionManager.beginTransaction();     // Start transaction

        User user = (User) session.createCriteria(User.class)
                .add(Restrictions.eq("username", name))
                .add(Restrictions.eq("password", password))
                .uniqueResult();

        HibernateSessionManager.commitTransaction();        // Persist transaction

        if(user==null){return false;}                   // If there is no user return false

        return true;
    }

    @Override
    public void addUser(User user) {

        try{
            session = HibernateSessionManager.beginTransaction();      // Start transaction
            session.save(user);
            HibernateSessionManager.commitTransaction();
        }        catch (HibernateException ex) {     // insert info on error in console
            HibernateSessionManager.rollbackTransaction();         // Persist transaction
        }
    }

    @Override
    public User findByName(String name) {

        session = HibernateSessionManager.beginTransaction();       // Start transaction

        Query find = session.createQuery("from User where username = :name");

        find.setString("name", name);
        User userName = (User) find.uniqueResult();

        HibernateSessionManager.commitTransaction();            // Persist transaction

        return userName;

    }

    @Override
    public int count() {

        session = HibernateSessionManager.beginTransaction();   // Start transaction

        Query count = session.createQuery("SELECT COUNT (*) from User") ;
        Integer number = count.getMaxResults();

        HibernateSessionManager.commitTransaction();             // Persist transaction

        return number;
    }

    public String getType(){
        return UserService.class.getSimpleName();
    }
}


package org.academiadecodigo.roothless.service.user;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by codecadet on 13/03/17.
 */
public class HibernateUserService implements UserService {


    Session session;

    @Override
    public boolean authenticate(String username, String password) {

        boolean result = false;

        session = HibernateSessionManager.beginTransaction();     // Start transaction

        Query query = session.createQuery("from User where username= :username");
        query.setString("username", username);
        query.setString("password", password);
        result = findOne(query) !=null;

        return result;


//        User user = (User) session.createCriteria(User.class)
//                .add(Restrictions.eq("username", username))
//                .add(Restrictions.eq("password", password))
//                .uniqueResult();
//
//        HibernateSessionManager.commitTransaction();        // Persist transaction
//
//        if(user==null){return false;}                   // If there is no user return false
//
//        return true;
    }

    @Override
    public void addUser(User user) {

        try{
            session = HibernateSessionManager.beginTransaction();      // Start transaction

            // add a query to check if it exists
            session.save(user);
            HibernateSessionManager.commitTransaction();
        }        catch (HibernateException ex) {     // insert info on error in console
            HibernateSessionManager.rollbackTransaction();         // Persist transaction
        }
    }

    @Override
    public User findByName(String username) {

        session = HibernateSessionManager.beginTransaction();       // Start transaction

        Query find = session.createQuery("from User where username = :username");

        find.setString("name", username);
        User userName = (User) find.uniqueResult();

        HibernateSessionManager.commitTransaction();            // Persist transaction

        return userName;

    }

    @Override
    public int count() {

        int size = 0;

        try {
            session = HibernateSessionManager.beginTransaction();   // Start transaction

            size = ((Long) session.createQuery("SELECT COUNT (*) from User")
                    .uniqueResult())
                    .intValue();

            HibernateSessionManager.commitTransaction();             // Persist transaction
        } catch (HibernateException ex) {

            System.out.println("Error counting the number of users : " + ex.getMessage());
            HibernateSessionManager.rollbackTransaction();
        }
        return size;
    }

    public String getType(){
        return UserService.class.getSimpleName();
    }
}


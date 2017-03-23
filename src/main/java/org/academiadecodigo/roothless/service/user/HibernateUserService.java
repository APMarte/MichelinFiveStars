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


    private Session session;

    @Override
    public boolean authenticate(String username, String password) {

        boolean result = false;

            try {
                session = HibernateSessionManager.beginTransaction();     // Start transaction

                Query query = session.createQuery("from User where username = :username");
                query.setString("username", username);
                result = findOne(query) != null;
            } catch (HibernateException ex) {
                System.out.println("Error authenticating: " + ex.getMessage());
                HibernateSessionManager.rollbackTransaction();
            }

        return result;
    }

    @Override
    public void addUser(User user) {

        try {
            session = HibernateSessionManager.beginTransaction();      // Start transaction
            Query query = session.createQuery("from User where username = :username");
            query.setString("username", user.getUsername());
            if (findOne(query) != null) {
                ;
                // add a query to check if it exists
                session.save(user);
                HibernateSessionManager.commitTransaction();
            }
        } catch(HibernateException ex){     // insert info on error in console
                HibernateSessionManager.rollbackTransaction();         // Persist transaction
            }
        }

    @Override
    public User findByName(String username) {

        session = HibernateSessionManager.beginTransaction();       // Start transaction

        Query find = session.createQuery("from User where username = :username");

        find.setString("username", username);
        User userName = (User) find.uniqueResult();

        HibernateSessionManager.commitTransaction();            // Persist transaction

        return userName;

    }

private User findOne(Query query){
    return (User) query.uniqueResult();
}

    @Override
    public int count() {

        int size = 0;

        try {
            session = HibernateSessionManager.beginTransaction();   // Start transaction

            size = ((Long) session.createQuery("SELECT COUNT (*) from User ")
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


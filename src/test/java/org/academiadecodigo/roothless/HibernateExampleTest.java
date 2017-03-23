package org.academiadecodigo.roothless;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by codecadet on 20/03/17.
 */
public class HibernateExampleTest {

    Session session;

    @Before
    public void openSession(){
        session = HibernateSessionManager.beginTransaction();
    }

    @Test
    public void addUser(){
        User user = new User("Antonio" , "abc@gmail.com", "1234");
        User user1= new User("EltonJorge", "tinydancer@eltonjorge.com", "nikita");
        User user2= new User("Elton Jorge", "indahouse@seujorge.com", "nikita2");

//        user.setUsername("");
//        user.setPassword("Nikita");
//        user.setEmail("tinydancer@eltonjorge.com");

        session.save(user);
        session.save(user1);
        session.save(user2);
    }

    @Test
    public void removeUser(){

        User user = new User("Antonio" , "abc@gmail.com", "1234");
        User user1= new User("EltonJorge", "tinydancer@eltonjorge.com", "nikita");
        User user2= new User("Elton Jorge", "indahouse@seujorge.com", "nikita2");


        session.save(user);
        session.save(user1);
        session.save(user2);

        user = session.load(User.class, 1);
        System.out.println(user);
        session.delete(user);

    }

    @Test
    public void editWithinSession(){

        User user = new User("Antonio" , "abc@gmail.com", "1234");
        User user1= new User("EltonJorge", "tinydancer@eltonjorge.com", "nikita");
        User user2= new User("Elton Jorge", "indahouse@seujorge.com", "nikita2");

        session.save(user);
        session.save(user1);
        session.save(user2);

        user=session.load(User.class,2);
        user.setEmail("eltonJorge@2hot4me.xXx");
    }

    @Test
    public void editOutsideSession(){

        User user = new User("Antonio" , "abc@gmail.com", "1234");
        User user1= new User("EltonJorge", "tinydancer@eltonjorge.com", "nikita");
        User user2= new User("Elton Jorge", "indahouse@seujorge.com", "nikita2");

        session.save(user);
        session.save(user1);
        session.save(user2);

        user=session.load(User.class,2);
        user.setEmail("ola@ola.com");
        session.evict(user);

        session.update(user);
    }

    @After
    public void endSession(){
        HibernateSessionManager.commitTransaction();
    }
}

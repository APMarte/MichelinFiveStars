package org.academiadecodigo.roothless.service.user;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.persistence.TransactionException;
import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;


/**
 * Created by codecadet on 23/03/17.
 */
public class UserServiceImpl implements UserService{

    private UserDao userDao;
    private TransactionManager transactionManager;

    public UserServiceImpl(UserDao userDao, TransactionManager transactionManager) {
        this.userDao = userDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public String getType(){
        return UserService.class.getSimpleName();
    }

  //// +++++++++++++++++++++

    @Override
    public boolean authenticate(String name, String password) {

        User user = null;
        try{
            transactionManager.begin();
            user = userDao.read(name);
            System.out.println("authenticating....");
            transactionManager.commit();
        }catch (TransactionException transactionException){
            System.out.println("Error during authentication: " + transactionException.getMessage());
            transactionManager.rollback();
        }
        return user != null && user.getPassword().equals(password); // see if we should use the hashed password or not
    }



    @Override
    public void addUser(User user) {

        try{
            transactionManager.begin();
            if(userDao.read(user.getUsername())==null){
                userDao.create(user);
            }
            transactionManager.commit();
            System.out.println("User added successfully");

        }catch (TransactionException transactionException){
            System.out.println(transactionException.getMessage());
            transactionManager.rollback();
            System.out.println("Error, could not add user: " + transactionException.getMessage());
        }
    }

    @Override
    public User findByName(String name) {

        User user=null;

        try {
            transactionManager.begin();
            user = userDao.read(name);
            transactionManager.commit();

        }catch (TransactionException transactionException){
            System.out.println("Error, could not find user: " + transactionException.getMessage());
            transactionManager.rollback();
        }
        return user;
    }

    @Override
    public int count() {
        int size = 0;
        try{
            size=userDao.listSize();
            transactionManager.commit();
        }catch (TransactionException transactionException){
            System.out.println("Error, could not find user: " + transactionException.getMessage());
            transactionManager.rollback();
        }
        return size;
    }
}

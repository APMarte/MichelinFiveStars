package org.academiadecodigo.roothless.service.user;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;


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

    public String getType(){
        return UserService.class.getSimpleName();
    }

  //// +++++++++++++++++++++

    @Override
    public boolean authenticate(String name, String password) {
        return false;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }
}

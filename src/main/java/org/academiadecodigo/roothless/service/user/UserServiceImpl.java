package org.academiadecodigo.roothless.service.user;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public class UserServiceImpl implements UserDao{


    //use a list has a kind of temp database

    private List<User> users;

    public UserServiceImpl(){
        users = new ArrayList<>();
//          Add some users here to test?
    }


    //retrive list of students from the database
    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public void updateUser(User user) {
        users.get(user.getId()).setUsername(user.getUsername());
        System.out.println("User: " + user.getUsername() + ",  was updated in the database");
    }

    @Override
    public void deleteUser(User user) {
        users.remove(user.getId());
        System.out.println("User: " + user.getUsername() + ", was deleted from database");
    }

}

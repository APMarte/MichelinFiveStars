package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.model.User;

import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public interface UserDao {


        public List<User> getAll();
        public User getUser(int id);
        public void updateUser(User user);
        public void deleteUser(User user);

    }


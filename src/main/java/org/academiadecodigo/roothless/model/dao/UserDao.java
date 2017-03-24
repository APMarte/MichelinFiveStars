package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.model.User;

import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public interface UserDao {

    void create(User user);
    User read(String user);
    void update(User user);
    void delete(User user);

    User findById(int id);
    int listSize();

}


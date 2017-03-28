package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.model.User;

import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public interface UserDao extends Dao<User>{

    User read(String name);
    int listSize();

}


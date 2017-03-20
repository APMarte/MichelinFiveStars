package org.academiadecodigo.roothless.service.user;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.Service;

/**
 * Created by codecadet on 07/03/17.
 */
public interface UserService extends Service {

    boolean authenticate(String name, String password);
    void addUser(User user);
    User findByName(String name);
    int count();
}

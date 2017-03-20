package org.academiadecodigo.roothless.service.user;

import org.academiadecodigo.bootcamp.model.User;

import java.util.LinkedList;

/**
 * Created by codecadet on 07/03/17.
 */
public class MockUserService implements UserService {

    private User user;
    private LinkedList<User> list;

    public MockUserService() {
        this.list = new LinkedList<>();
    }

    @Override
    public boolean authenticate(String name, String password) {

        if(findByName(name)==null){
            return false;
        }

        if(findByName(name).getPassword().equals(password)){
            return true;
        }

        return false;
    }

    @Override
    public void addUser(User user) {
       list.add(user);
    }

    @Override
    public User findByName(String name) {

        for (User searchUser: list) {
            if(searchUser.getUsername().equals(name)){
                return searchUser;
            }
        }
        return null;
    }

    @Override
    public int count() {
        return list.size();
    }

    public String getType(){
        return UserService.class.getSimpleName();
    }
}

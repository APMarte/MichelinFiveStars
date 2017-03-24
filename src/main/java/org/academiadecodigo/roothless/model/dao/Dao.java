package org.academiadecodigo.roothless.model.dao;

import javax.management.relation.Role;

/**
 * Created by codecadet on 24/03/17.
 */
public interface Dao <T> {

    void create(T t);

    void update(T t);

    void delete(T t);
}

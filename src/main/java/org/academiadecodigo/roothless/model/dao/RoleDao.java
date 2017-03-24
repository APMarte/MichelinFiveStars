package org.academiadecodigo.roothless.model.dao;

import org.academiadecodigo.roothless.model.User;

import javax.management.relation.Role;
import java.util.List;

/**
 * Created by codecadet on 23/03/17.
 */
public interface RoleDao extends Dao {

    User findRole(String name);

}

package org.academiadecodigo.roothless.model.dao.hibernate;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.AbstractDao;
import org.academiadecodigo.roothless.model.dao.RoleDao;
import org.academiadecodigo.roothless.persistence.TransactionException;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.management.relation.Role;

/**
 * Created by codecadet on 23/03/17.
 */
public class HibernateRoleDao extends AbstractDao <Role> implements RoleDao {

    private Session session;

    public HibernateRoleDao() {
        session = HibernateSessionManager.getSession();
    }

    @Override
    public User findRole(String name) {
        User user;
        try {

            user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("username", name))
                    .uniqueResult();

        } catch (HibernateException ex) {
            throw new TransactionException(ex);
        }

        return user;
    }

    //    @Override
//    public void create(Role role) {
//
//        try {
//            session.save(role);
//
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//
//    }
//
//    @Override
//    public User findRole(String name) {
//        User user;
//        try {
//
//            user = (User) session.createCriteria(User.class)
//                    .add(Restrictions.eq("username", name))
//                    .uniqueResult();
//
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//
//        return user;
//    }
//
//    @Override
//    public void update(Role role) {
//
//        try {
//            session.merge(role);
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//    }
//
//    @Override
//    public void delete(Role role) {
//
//        try {
//            session.delete(role);
//        } catch (HibernateException ex) {
//            throw new TransactionException(ex);
//        }
//
//    }
} // End


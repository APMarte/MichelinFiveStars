package org.academiadecodigo.roothless.persistence.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by codecadet on 20/03/17.
 */
public final class HibernateSessionManager {

    private final static String HIBERNATE_CONFIG = "/persistence/hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    private HibernateSessionManager() {
    }

    static {
        try {
            // Hold services needed by Hibernate
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure(HIBERNATE_CONFIG) // Load settings from hibernate.cfg.xml
                    .build();

            sessionFactory = new MetadataSources(serviceRegistry)
                        .buildMetadata() // Tell Hibernate about sources of metadata (database mappings)
                        .buildSessionFactory();

        }catch (HibernateException ex) {
            throw new ExceptionInInitializerError("Error creating hibernate session factory: " + ex.getMessage());
        }
    }

    /**
     * Obtains the current session from the Hibernate session factory
     *
     * @return The current session
     */
    public static Session getSession() {

        /*
         Due to automatic session context management,
         (current_session_context_class = thread)
         Hibernate will automatically open a new session if needed.

         Closing the session is not required, Hibernate will close
         the session when transaction is committed or rollback.
         */

        return sessionFactory.getCurrentSession();
    }

    /**
     * Initiates a new transaction
     *
     * @return the session associated with the transaction
     */

    public static Session beginTransaction(){

        Session session = getSession();
        session.getTransaction().begin();
        return session;
    }

    /**
     * Terminates the current transaction
     */
    public static void commitTransaction(){
        getSession().getTransaction().commit();
    }

    /**
     * Rollback the current transaction
     */
    public static void rollbackTransaction(){
        getSession().getTransaction().rollback();
    }

    /**
     * Closes the Hibernate Session factory,
     * necessary for application to quit
     */
    public static void close() {
        sessionFactory.close();
    }

}

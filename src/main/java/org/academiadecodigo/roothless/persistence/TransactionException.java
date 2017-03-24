package org.academiadecodigo.roothless.persistence;

import org.hibernate.HibernateException;

/**
 * Created by codecadet on 23/03/17.
 */
public class TransactionException extends RuntimeException{

    public TransactionException(Throwable cause) {
        super(cause);
    }
}

package org.academiadecodigo.roothless.persistence;

/**
 * Created by codecadet on 23/03/17.
 */
public interface TransactionManager {

    public void begin();
    public void commit();
    public void rollback();

}

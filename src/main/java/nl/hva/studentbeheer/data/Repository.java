/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data;

/**
 * Definitie van een repository.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 * @param <T> type of the object 
 */
public abstract class Repository<T> implements IRepository<T>{

    public abstract void setConnection(RepositoryConnection rcon);
    
}

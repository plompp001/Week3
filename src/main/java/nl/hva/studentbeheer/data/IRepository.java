/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data;

import java.util.List;
import nl.hva.studentbeheer.data.specs.ISpecification;

/**
 * Interface voor repository.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 * @param <T> type of the object to save.
 */
public interface IRepository<T> {
    void add(T item);

    void add(Iterable<T> items);

    void update(T item);

    void remove(T item);

    void remove(ISpecification specification);

    List<T> query(ISpecification specification);
    
}

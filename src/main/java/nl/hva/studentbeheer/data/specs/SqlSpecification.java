/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.specs;

/**
 * Definitie van sql specificatie.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public abstract class SqlSpecification implements ISpecification {
    
    public abstract String toSqlQuery();
    
}

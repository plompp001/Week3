/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.specs;

/**
 * Definitie van file specificatie.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public abstract class FileSpecification implements ISpecification {
    
    public abstract String toFileQuery();

}

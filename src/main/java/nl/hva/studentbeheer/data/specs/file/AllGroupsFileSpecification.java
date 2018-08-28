/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data.specs.file;

import nl.hva.studentbeheer.data.specs.FileSpecification;

/**
 * File specificatie voor alle groepen.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AllGroupsFileSpecification extends FileSpecification {

    @Override
    public String toFileQuery() {
        return "ALL";
    }

}

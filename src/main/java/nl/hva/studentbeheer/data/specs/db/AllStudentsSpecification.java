/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.specs.db;

import nl.hva.studentbeheer.data.specs.SqlSpecification;

/**
 * Sql specificatie voor alle studenten.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AllStudentsSpecification extends SqlSpecification {

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT straat, huisnr, plaats, voornaam, achternaam, geslacht" +
                        " FROM %1$s T1" +
                        " INNER JOIN %2$s T2 ON T1.adres_id = T2.adres_id" +
                        " ORDER BY `achternaam` ASC;",
                "Student",
                "Adres");
        
    }

}

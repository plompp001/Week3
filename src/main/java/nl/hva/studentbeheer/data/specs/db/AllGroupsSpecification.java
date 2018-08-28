/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data.specs.db;

import nl.hva.studentbeheer.data.specs.SqlSpecification;

/**
 * Sql specificatie voor alle groepen.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AllGroupsSpecification extends SqlSpecification {

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT straat, huisnr, plaats, voornaam, achternaam, geslacht, gnaam" +
                        " FROM %1$s T1" +
                        " LEFT JOIN %2$s T2 ON T2.groep_id = T1.groep_id" +
                        " LEFT JOIN %3$s T3 ON T3.student_id = T2.student_id" +
                        " INNER JOIN %4$s T4 ON T4.adres_id = T3.adres_id" +
                " ORDER BY `gnaam` ASC;",
                "Groep",
                "Groeplid",
                "Student",
                "Adres");
    }

}

/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.specs.db;

import nl.hva.studentbeheer.data.specs.SqlSpecification;

/**
 * Sql specificatie voor alle adressen.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AllAddressesSpecification extends SqlSpecification {

    @Override
    public String toSqlQuery() {
        return String.format(
                "SELECT * FROM %1$s ORDER BY `%2$s` ASC;",
                "Adres",
                "plaats");

    }

}

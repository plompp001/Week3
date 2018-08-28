/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data.specs.db;

import nl.hva.studentbeheer.data.specs.SqlSpecification;

/**
 * Sql specificatie voor een adres..
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AddressSpecification extends SqlSpecification {
    private final String straat;
    private final int huisnummer;
    private final String plaats;
    
    public AddressSpecification(String straat, int huisnummer, String plaats) {
        this.straat = straat;
        this.huisnummer = huisnummer;
        this.plaats = plaats;
    }
    
    @Override
    public String toSqlQuery() {
        return toSqlQuery(straat, huisnummer, plaats);
    }
    
    private String toSqlQuery(String straat, int huisnummer, String plaats) {
        return String.format(
                "SELECT * FROM %1$s WHERE %2$s = %3$s AND %4$s = %5$d AND %6$s = %7$s;",
                "Adres",
                "straat", straat,
                "huisnummer", huisnummer,
                "plaats", plaats);
    }

}

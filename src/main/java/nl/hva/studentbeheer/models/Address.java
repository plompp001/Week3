/*
 * This file was created to facilitate lessons at HBO-ICT@HvA.
 */
package nl.hva.studentbeheer.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Adres informatie.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl)>
 * @author Dennis Breuker <d.m.breuker@hva.nl>
 */
public class Address implements Serializable {

    private final String street;
    private final int houseNumber;
    private final String city;

    /**
     * Adres constructor
     * 
     * @param street straatnaam
     * @param houseNumber houseNumber (kan alleen geheel getal zijn)
     * @param city plaatsnaam
     */
    public Address(String street, int houseNumber, String city) {
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    /**
     * Test of adres gelijk is aan gegeven adres.
     * Adressen zijn gelijk als street, houseNumber en city hetzelfde zijn.
     * 
     * @param obj gegeven adres
     * @return true als adressen gelijk zijn anders false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            return this.toString().equals(((Address)obj).toString());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.street);
        hash = 19 * hash + this.houseNumber;
        hash = 19 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public String toString() {
        return street + " " + houseNumber + ", " + city;
    }
}

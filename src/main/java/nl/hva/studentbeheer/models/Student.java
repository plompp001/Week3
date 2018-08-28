/*
 * This file was created to facilitate lessons at HBO-ICT@HvA.
 */
package nl.hva.studentbeheer.models;

import nl.hva.studentbeheer.enums.Gender;
import java.io.Serializable;
import java.util.Objects;

/**
 * Student informatie.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl)>
 * @author Dennis Breuker <d.m.breuker@hva.nl>
 */
public class Student implements Serializable {    
    private final String firstname;
    private final String lastname;
    private final Address address;
    private final Gender gender;

    /**
     * Student constructor
     * 
     * @param firstname firstname van student
     * @param lastname lastname van student
     * @param address address van student
     * @param gender gender van student
     */
    public Student(String firstname, String lastname, Address address, Gender gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
    
    /**
     * get address
     * 
     * @return address van student
     */
    public Address getAddress() {
        return address;
    }

    /**
     * get gender
     * 
     * @return gender van student
     */
    public Gender getGender() {
        return gender;
    }
    
    /**
     * Test of student gelijk is aan gegeven student.
     * Studenten zijn gelijk als firstname, lastname, gender en address hetzelfde zijn.
     * 
     * @param obj gegeven student
     * @return true als studenten gelijk zijn anders false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return this.toString().equals(((Student)obj).toString());
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.firstname);
        hash = 23 * hash + Objects.hashCode(this.lastname);
        hash = 23 * hash + Objects.hashCode(this.address);
        hash = 23 * hash + Objects.hashCode(this.gender);
        return hash;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname + ", " + 
                ((gender==Gender.MAN)?"m":"v") + " - " + address.toString();
    }    
    
}

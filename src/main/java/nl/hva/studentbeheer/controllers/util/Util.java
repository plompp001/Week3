/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.controllers.util;

import javafx.collections.ObservableList;
import nl.hva.studentbeheer.models.Address;
import nl.hva.studentbeheer.models.Group;
import nl.hva.studentbeheer.models.Student;

/**
 * Hulp methodes.
 *
 * @author Folkert Boonstra (F.K.Boonstra@hva.nl)
 */
public final class Util {

    private Util() {
    }

    /**
     * Test of een string null of leeg is
     *
     * @param str De te testen string
     * @return true als de string null of leeg is, anders false
     */
    public static boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * Voeg adres toe aan lijst als adres nog niet in de lijst zit.
     *
     * @param aList observable lijst met adressen
     * @param na nieuw adres
     * @return true als adres is toegevoegd
     */
    public static boolean addNonExistingAddress(ObservableList<Address> aList, Address na) {
        boolean found = false;
        for (Address a : aList) {
            if (a.equals(na)) {
                found = true;
                break;
            }
        }
        if (!found) {
            aList.add(na);
        }
        return !found;
    }

    /**
     * Voeg student toe aan lijst als student nog niet in de lijst zit.
     *
     * @param sList observable lijst met studenten
     * @param ns nieuwe student
     * @return true als student is toegevoegd
     */
    public static boolean addNonExistingStudent(ObservableList<Student> sList, Student ns) {
        boolean found = false;
        for (Student s : sList) {
            if (s.equals(ns)) {
                found = true;
                break;
            }
        }
        if (!found) {
            sList.add(ns);
        }
        return !found; // true als student is toegevoegd
    }

    /**
     * Voeg groep toe aan lijst als groep nog niet in de lijst zit.
     *
     * @param gList observable lijst met groepen
     * @param ng nieuwe groep
     * @return true als groep is toegevoegd
     */
    public static boolean addNonExistingGroup(ObservableList<Group> gList, Group ng) {
        boolean found = false;
        for (Group g : gList) {
            if (g.equals(ng)) {
                found = true;
                break;
            }
        }
        if (!found) {
            gList.add(ng);
        }
        return !found; // true als groep is toegevoegd
    }
}

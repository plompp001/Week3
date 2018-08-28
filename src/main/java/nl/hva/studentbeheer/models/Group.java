/*
 * This file was created to facilitate lessons at HBO-ICT@HvA.
 */
package nl.hva.studentbeheer.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Groep informatie
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl)>
 * @author Dennis Breuker <d.m.breuker@hva.nl>
 */
public class Group implements Serializable {

    static final int MAX_GROUPSIZE = 5;

    private static  ArrayList<String> groupNames = new ArrayList<>();
    private final String name;
    private final ArrayList<Student> students;

    /**
     * Groep constructor.
     * 
     * @param naam name van de groep
     */
    public Group(String naam) {
        this.name = naam;
        groupNames.add(naam);
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Voeg een student toe aan de groep als dat kan.
     * 
     * @param student toe te voegen student.
     * @return true als het gelukt is de student toe te voegen anders false
     */
    public boolean addStudent(Student student) {
        if (students.size() < MAX_GROUPSIZE) {
            students.add(student);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Geef de lijst met students in de groep terug.
     * 
     * @return lijst met students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * Test of een gegeven student al in de groep zit.
     * 
     * @param student gegeven student
     * @return true als student in de groep zit anders false
     */
    public boolean studentInGroup(Student student) {
        boolean found = false;
        for (Student s : students) {
            if (s.toString().equals(student.toString())) {
                found = true;
                break;
            }
        }
        return found;
    }

    /**
     * Test of er al een groep is met de gegeven name.
     * 
     * @param name gegeven name
     * @return true als er al een groep is met de gegeven name
     */
    public static boolean exists(String name) {
        return groupNames.contains(name);
    }
    
    /**
     * Test of groep gelijk is aan gegeven groep.
     * Groepen zijn gelijk als de namen hetzelfde zijn.
     * 
     * @param obj gegeven groep
     * @return true als groepen gelijk zijn anders false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Group) {
            return this.toString().equals(((Group)obj).toString());
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.students);
        return hash;
    }

    /**
     * Geef de string representatie van de groep.
     * 
     * @return name van de groep
     */
    @Override
    public String toString() {
        return name;
    }

}

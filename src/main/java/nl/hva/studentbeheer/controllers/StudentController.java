/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.studentbeheer.controllers;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.file.StudentFileRepository;
import nl.hva.studentbeheer.data.specs.db.AllStudentsSpecification;
import nl.hva.studentbeheer.data.specs.file.AllStudentsFileSpecification;
import static nl.hva.studentbeheer.controllers.util.Util.nullOrEmpty;
import nl.hva.studentbeheer.models.Address;
import nl.hva.studentbeheer.models.Student;
import nl.hva.studentbeheer.enums.Gender;
import static nl.hva.studentbeheer.controllers.util.Util.addNonExistingStudent;
import static nl.hva.studentbeheer.controllers.util.Util.addNonExistingAddress;

/**
 * FXML Controller class
 *
 * @author pieter
 */
public class StudentController {

    private final ArrayList<Address> myAddresses;
    private ObservableList<Address> addressData;
    private final ArrayList<Student> myStudents;
    private final ObservableList<Student> studentData;
    private final Repository rep;

    @FXML
    TextField tfFirstname;
    @FXML
    TextField tfLastname;
    @FXML
    ComboBox cbAddress;
    @FXML
    Label lblMessage;
    @FXML
    RadioButton rbMan;
    @FXML
    ListView lvStudents;

    /**
     * Constructor voor StudentController.
     *
     * @param rep
     */
    public StudentController(Repository rep) {
        this.rep = rep;
        myAddresses = new ArrayList<>();
        addressData = FXCollections.observableList(myAddresses);
        myStudents = new ArrayList<>();
        studentData = FXCollections.observableList(myStudents);
        cbAddress = new ComboBox<>();
    }

    /**
     * Voeg student toe aan GUI.
     */
    public void addStudent() {
        Student student = createStudent();
        if (student != null && !addNonExistingStudent(studentData, student)) {
            showMessage("Student bestaat al!");
        }

        if (studentData != null) {
            lvStudents.setItems(studentData);
        }
    }

    /**
     * Bewaar studenten in persistent storage.
     */
    public void saveStudents() {
        if (myStudents.isEmpty()) {
            showMessage("De lijst met studenten is leeg!");
        } else {
            rep.add(myStudents);
            showMessage(myStudents.size() + " Studenten bewaard.");
        }
    }

    /**
     * Laad studenten uit persistent storage.
     */
    public void loadStudents() {

        addressData = cbAddress.getItems();

        int amount = 0;
        List<Student> newStudents;

        if (rep instanceof StudentFileRepository) {
            newStudents = rep.query(new AllStudentsFileSpecification());
        } else {
            newStudents = rep.query(new AllStudentsSpecification());
        }
        if (newStudents == null || newStudents.isEmpty()) {
            return;
        }

        // Alleen niet bestaande studenten worden toegevoegd:
        for (int i = 0; i < newStudents.size(); i++) {
            if (!addNonExistingStudent(studentData, newStudents.get(i))) {
                amount++;
            }

            addNonExistingAddress(addressData, newStudents.get(i).getAddress());            
        }

        if (addressData != null) {
                cbAddress.setItems(addressData);
            }
        
        if (studentData != null) {
            lvStudents.setItems(studentData);
        }

        switch (amount) {
            case 0:
                showMessage(newStudents.size() + " Studenten geladen.");
                break;
            case 1:
                showWarning((newStudents.size() - amount) + " Studenten geladen: " + amount + " bestond al.");
                break;
            default:
                showWarning((newStudents.size() - amount) + " Studenten geladen: " + amount + " bestonden al.");
                break;
        }
    }

    /**
     * Maak een nieuwe student.
     */
    private Student createStudent() {
        Address studentAddress = (Address) cbAddress.getValue();
        if (studentAddress == null) {
            showWarning("Er moet eerst een adres worden geselecteerd!");
            return null;
        }
        String firstname = tfFirstname.getText();
        String lastname = tfLastname.getText();
        if (nullOrEmpty(firstname) || nullOrEmpty(lastname)) {
            showWarning("Alle velden moeten worden ingevuld");
            return null;
        }

        Gender gender = rbMan.isSelected() ? Gender.MAN : Gender.VROUW;
        Student student = new Student(firstname, lastname, studentAddress, gender);
        showMessage("Student " + student.toString() + " is aangemaakt.");

        return student;
    }

    /**
     * Toon melding.
     */
    private void showMessage(String message) {
        lblMessage.setText(message);
        lblMessage.setTextFill(Color.BLACK);
    }

    /**
     * Toon waarschuwing.
     */
    private void showWarning(String warning) {
        lblMessage.setText(warning);
        lblMessage.setTextFill(Color.RED);
    }

}

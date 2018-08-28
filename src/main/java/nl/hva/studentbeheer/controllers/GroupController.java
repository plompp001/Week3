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
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.file.GroupFileRepository;
import nl.hva.studentbeheer.data.specs.db.AllGroupsSpecification;
import nl.hva.studentbeheer.data.specs.file.AllGroupsFileSpecification;
import static nl.hva.studentbeheer.controllers.util.Util.nullOrEmpty;
import nl.hva.studentbeheer.models.Group;
import nl.hva.studentbeheer.models.Student;
import static nl.hva.studentbeheer.controllers.util.Util.addNonExistingGroup;
import static nl.hva.studentbeheer.controllers.util.Util.addNonExistingStudent;

/**
 * Groep Controller.
 *
 * @author Pieter Plomp <pieter.plomp@hva.nl>
 */
public class GroupController {

    private final ArrayList<Group> myGroups;
    private final ObservableList<Group> groupData;
    private Group activeGroup;
    private final ArrayList<Student> myStudentGroup;
    private final ObservableList<Student> studentGroupData;
    private final Repository rep;

    @FXML
    TextField tfNameGroup;
    @FXML
    ComboBox cbGroup;
    @FXML
    ComboBox cbStudent;
    @FXML
    Label lblMessage;
    @FXML
    ListView lvGroupMembers;

    /**
     * Constructor voor GroepController.
     *
     * @param rep
     */
    public GroupController(Repository rep) {
        this.rep = rep;
        activeGroup = null;
        myGroups = new ArrayList<>();
        groupData = FXCollections.observableList(myGroups);
        myStudentGroup = new ArrayList<>();
        studentGroupData = FXCollections.observableList(myStudentGroup);
        cbGroup = new ComboBox<>();
        cbStudent = new ComboBox<>();
    }

    /**
     * Voeg nieuwe group toe.
     */
    public void addGroup() {
        Group newGroup = createGroup();
        if (newGroup != null && !addNonExistingGroup(groupData, newGroup)) {
            showWarning("Groep bestaat al!");
        }

        if (groupData != null) {
            cbGroup.setItems(groupData);
            cbGroup.setValue(newGroup);
        }
    }

    /**
     * Verwerk nieuwe geselecteerd group.
     */
    public void selectedGroupChanged() {
        activeGroup = (Group) cbGroup.getValue();
        studentGroupData.clear();
        studentGroupData.addAll(activeGroup.getStudents());
        lvGroupMembers.setItems(null);
        lvGroupMembers.setItems(studentGroupData);
    }

    /**
     * Methode om student toe te voegen aan een activeGroup.
     */
    public void addStudent() {
        if (activeGroup == null) {
            showWarning("Er moet eerst een groep worden geselecteerd!");
            return;
        }
        Student student = (Student) cbStudent.getValue();
        if (student == null) {
            showWarning("Er moet eerst een student worden geselecteerd!");
            return;
        }
        if (activeGroup.studentInGroup(student)) {
            showWarning("Student " + student + " zit al in groep!");
        } else {
            boolean success = activeGroup.addStudent(student);
            if (success) {
                studentGroupData.add(student);
                showMessage("Student " + student + " toegevoegd.");
            } else {
                showWarning("Helaas, groep " + activeGroup + " zit vol!");
            }
        }
    }

    /**
     * Bewaar groepen in persistent storage.
     */
    public void saveGroups() {
        if (myGroups.isEmpty()) {
            showMessage("De lijst met groepen is leeg!");
        } else {
            rep.add(myGroups);
            showMessage(myGroups.size() + " Groepen bewaard.");
        }
    }

    /**
     * Laad studenten uit persistent storage.
     */
    public void loadGroups() {
        List<Group> newGroups;
        if (rep instanceof GroupFileRepository) {
            newGroups = rep.query(new AllGroupsFileSpecification());
        } else {
            newGroups = rep.query(new AllGroupsSpecification());
        }
        if (newGroups != null && !newGroups.isEmpty()) {
            processLoadedGroups(newGroups);
        }        
    }   

    /**
     * Maak een nieuwe activeGroup.
     */
    private Group createGroup() {
        String name = tfNameGroup.getText();

        if (nullOrEmpty(name)) {
            showWarning("Alle velden moeten worden ingevuld");
            return null;
        }

        Group group;
        if (Group.exists(name)) {
            group = null;
            showMessage("Groep " + name + " bestaat al.");
        } else {
            group = new Group(name);
            showMessage("Groep " + group.toString() + " is aangemaakt.");
        }
        return group;
    }

    /**
     * Verwerk geladen groepen.
     */
    private void processLoadedGroups(List<Group> newGroups) {
        int amount = 0;
        // Alleen niet bestaande groepen worden toegevoegd:
        for (int i = 0; i < newGroups.size(); i++) {
            if (!addNonExistingGroup(groupData,
                    newGroups.get(i))) {
                amount++;
            } else {
                // Als activeGroup is toegevoegd aan GUI moet dat ook voor student gebeuren:
                for (Student student : newGroups.get(i).getStudents()) {
                    addNonExistingStudent(cbStudent.getItems(),
                            student);
                }
            }
        }

        cbGroup.setItems(groupData);

        if (amount > 0) {
            if (amount == 1) {
                showWarning((newGroups.size() - amount) + " Groepen geladen: " + amount + " bestond al.");
            } else {
                showWarning((newGroups.size() - amount) + " Groepen geladen: " + amount + " bestonden al.");
            }
        } else {
            showMessage(newGroups.size() + " Groepen geladen.");
        }
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

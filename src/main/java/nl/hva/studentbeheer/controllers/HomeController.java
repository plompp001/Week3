/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.studentbeheer.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.hva.studentbeheer.data.Repository;

/**
 * Home Controller.
 *
 * @author Pieter Plomp <pieter.plomp@hva.nl>
 */
public class HomeController {

    private StudentController newStudentController;
    private AddressController addressController;
    private GroupController groupController;
    Stage addressStage;
    Stage studentStage;
    Stage groupStage;

    /**
     * Initialiseer GUI en data access voor deze controller.
     *
     * @param reps
     * @throws java.io.IOException
     */
    public void initialize(Repository[] reps) throws IOException {

        this.addressController = new AddressController(reps[0]);
        this.newStudentController = new StudentController(reps[1]);
        this.groupController = new GroupController(reps[2]);

        loadAddressStage();
        loadStudentStage();
        loadGroupStage();
        setBindings();
    }

    /**
     * Toon adres scherm.
     */
    @FXML
    private void showAddressPane() throws IOException {
        addressStage.show();
    }

    /**
     * Toon student scherm.
     */
    @FXML
    private void showStudentPane() throws IOException {
        studentStage.show();
    }

    /**
     * Toon groep scherm.
     */
    @FXML
    private void showGroupPane() {
        groupStage.show();
    }

    /**
     * Laad adres scherm.
     */
    private void loadAddressStage() throws IOException {
        FXMLLoader addressLoader = new FXMLLoader();
        addressLoader.setLocation(getClass().getClassLoader().getResource("fxml/Address.fxml"));
        addressLoader.setController(this.addressController);
        Parent addressRoot = addressLoader.load();
        addressStage = new Stage();
        addressStage.setScene(new Scene(addressRoot));
        addressStage.setResizable(false);
        addressStage.setTitle("Studentbeheer - Adressen");
    }

    /**
     * Laad student scherm.
     */
    private void loadStudentStage() throws IOException {
        FXMLLoader studentLoader = new FXMLLoader();
        studentLoader.setLocation(getClass().getClassLoader().getResource("fxml/Student.fxml"));
        studentLoader.setController(this.newStudentController);
        Parent studentRoot = studentLoader.load();
        studentStage = new Stage();
        studentStage.setScene(new Scene(studentRoot));
        studentStage.setResizable(false);
        studentStage.setTitle("Studentbeheer - Studenten");
    }

     /**
     * Laad groep scherm.
     */
    private void loadGroupStage() throws IOException {
        FXMLLoader groupLoader = new FXMLLoader();
        groupLoader.setLocation(getClass().getClassLoader().getResource("fxml/Group.fxml"));
        groupLoader.setController(this.groupController);
        Parent groupRoot = groupLoader.load();
        groupStage = new Stage();
        groupStage.setScene(new Scene(groupRoot));
        groupStage.setResizable(false);
        groupStage.setTitle("Studentbeheer - Groepen");
    }

    /**
     * Maak connecties tussen elementen van verschillende schermen die
     * afhankelijk zijn van elkaar
     */
    private void setBindings() {
        this.newStudentController.cbAddress.itemsProperty().bindBidirectional(this.addressController.lvAddresses.itemsProperty());
        this.groupController.cbStudent.itemsProperty().bindBidirectional(this.newStudentController.lvStudents.itemsProperty());
    }

}

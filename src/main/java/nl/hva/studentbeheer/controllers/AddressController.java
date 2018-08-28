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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.file.AddressFileRepository;
import nl.hva.studentbeheer.data.specs.db.AllAddressesSpecification;
import nl.hva.studentbeheer.data.specs.file.AllAddressesFileSpecification;
import static nl.hva.studentbeheer.controllers.util.Util.nullOrEmpty;
import nl.hva.studentbeheer.models.Address;
import static nl.hva.studentbeheer.controllers.util.Util.addNonExistingAddress;

/**
 * Adres Controller.
 *
 * @author Pieter Plomp <pieter.plomp@hva.nl>
 */
public class AddressController {

    private final ArrayList<Address> myAddresses;
    private final ObservableList<Address> addressData;
    private final Repository rep;

    @FXML
    TextField tfStreet;
    @FXML
    TextField tfHouseNumber;
    @FXML
    TextField tfCity;
    @FXML
    ListView lvAddresses;
    @FXML
    Label lblMessage;

    public AddressController(Repository rep) {
        this.rep = rep;
        myAddresses = new ArrayList<>();
        addressData = FXCollections.observableList(myAddresses);
        lvAddresses = new ListView<>();
    }

    /**
     * Maak een nieuw address.
     */
    public void addAddress() {
        Address newAddress = createAddress();
        if (newAddress != null && !addNonExistingAddress(addressData, newAddress)) {
            showWarning("Adres bestaat al!");
        }

        if (addressData != null) {
            lvAddresses.setItems(addressData);
        }
    }

    /**
     * Bewaar adressen in persistent storage.
     */
    public void saveAddresses() {
        if (myAddresses.isEmpty()) {
            showMessage("De lijst met adressen is leeg!");
        } else {
            rep.add(myAddresses);
            showMessage(myAddresses.size() + " Adressen bewaard.");
        }
    }

    /**
     * Laad adressen uit persistent storage.
     */
    public void loadAddresses() {
        int amount = 0;
        List<Address> newAddresses;

        if (rep instanceof AddressFileRepository) {
            newAddresses = rep.query(new AllAddressesFileSpecification());
        } else {
            newAddresses = rep.query(new AllAddressesSpecification());
        }

        if (newAddresses == null || newAddresses.isEmpty()) {
            showMessage("Er zijn nog geen adressen opgeslagen.");
            return;
        }

        // Alleen niet bestaande adressen worden toegevoegd:          
        for (int i = 0; i < newAddresses.size(); i++) {
            if (!addNonExistingAddress(addressData, newAddresses.get(i))) {
                amount++;
            }
        }

        if (amount == 0) {
            showMessage(newAddresses.size() + " Adressen geladen.");
        } else if (amount == 1) {
            showWarning((newAddresses.size() - amount) + " Adressen geladen: " + amount + " bestond al.");
        } else {
            showWarning((newAddresses.size() - amount) + " Adressen geladen: " + amount + " bestonden al.");
        }

        if (addressData != null) {
            lvAddresses.setItems(addressData);
        }
    }

    /**
     * Maak een address object.
     */
    private Address createAddress() {
        String street = tfStreet.getText();
        String houseNumber = tfHouseNumber.getText();
        String city = tfCity.getText();
        if (nullOrEmpty(street) || nullOrEmpty(houseNumber)
                || nullOrEmpty(city)) {
            showWarning("Alle velden moeten worden ingevuld");
            return null;
        } else {
            int houseNr;
            try {
                houseNr = Integer.parseInt(houseNumber);
                Address address = new Address(street, houseNr, city);
                showMessage("Adres " + address.toString() + " aangemaakt.");
                return address;
            } catch (NumberFormatException ex) {
                showWarning("huisnummer moet geheel getal zijn!");
            }
            return null;
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

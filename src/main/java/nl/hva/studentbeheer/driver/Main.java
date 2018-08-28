/*
 * This file was created to facilitate lessons at HBO-ICT@HvA.
 */
package nl.hva.studentbeheer.driver;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.hva.studentbeheer.controllers.HomeController;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.db.AddressDBRepository;
import nl.hva.studentbeheer.data.db.DbConnection;
import nl.hva.studentbeheer.data.db.GroupDBRepository;
import nl.hva.studentbeheer.data.db.StudentDBRepository;
import nl.hva.studentbeheer.data.file.AddressFileRepository;
import nl.hva.studentbeheer.data.file.FileConnection;
import nl.hva.studentbeheer.data.file.GroupFileRepository;
import nl.hva.studentbeheer.data.file.StudentFileRepository;

/**
 * Driver voor OOP2 - PO2
 *
 * @author Folkert Boonstra (F.K.Boonstra@hva.nl)
 */
public class Main extends Application {

    private static final String USER = "root";
    private static final String PW = "folkert";
    private static final String MYFILE_ADDRESS = "OOP2_adresses";
    private static final String MYFILE_STUDENT = "OOP2_students";
    private static final String MYFILE_GROUP = "OOP2_groups";
    private String userName = USER;
    private String passWord = PW;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Build and show a stage.
     *
     * @param startStage
     * @throws java.io.IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // true voor FileStore; false voor Database:
        boolean useFileStore = true;
        // init repositories:
        Repository[] reps = initializeRepositories(useFileStore);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("fxml/Home.fxml"));       
        Parent root = loader.load();
        HomeController startupPageController = loader.getController();
        startupPageController.initialize(reps);
        
        Scene scene = new Scene(root);   
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Studentbeheer - Hoofdscherm");
        primaryStage.show();
    }

    /**
     * Stop de applicatie.
     */
    @Override
    public void stop() {
        try {
            Platform.exit();
        } catch (Exception ex) {
            System.out.print(ex.getMessage() + "\r\n");
        }
    }

     /**
     * Initialiseer repositories voor file of db management.
     */
    private Repository[] initializeRepositories(boolean useFileStore) {
        Repository[] reps = new Repository[3];

        // We kiezen een storage mechanisme:
        if (useFileStore) {
            AddressFileRepository afs = new AddressFileRepository();
            reps[0] = afs;
            FileConnection afsc = new FileConnection();
            afsc.setAddressConnection(getAddressFileStoreConnection());
            reps[0].setConnection(afsc);

            StudentFileRepository sfs = new StudentFileRepository();
            reps[1] = sfs;
            FileConnection sfsc = new FileConnection();
            sfsc.setStudentConnection(getStudentFileStoreConnection());
            reps[1].setConnection(sfsc);

            GroupFileRepository gfs = new GroupFileRepository();
            reps[2] = gfs;
            FileConnection gfsc = new FileConnection();
            gfsc.setGroupConnection(getGroupFileStoreConnection());
            reps[2].setConnection(gfsc);
        } else {
            AddressDBRepository arep = new AddressDBRepository();
            reps[0] = arep;
            DbConnection dbc = new DbConnection(getDbConnection());
            reps[0].setConnection(dbc);

            StudentDBRepository srep = new StudentDBRepository();
            reps[1] = srep;
            reps[1].setConnection(dbc);

            GroupDBRepository grep = new GroupDBRepository();
            reps[2] = grep;
            reps[2].setConnection(dbc);
        }
        return reps;
    }

     /**
     * Connectie voor de adres file.
     */
    private String getAddressFileStoreConnection() {
        return MYFILE_ADDRESS;
    }

     /**
     * Connectie voor de student file.
     */
    private String getStudentFileStoreConnection() {
        return MYFILE_STUDENT;
    }

    /**
     * Connectie voor de groep file.
     */
    private String getGroupFileStoreConnection() {
        return MYFILE_GROUP;
    }

    /**
     * Connectie voor database.
     */
    private Connection getDbConnection() {
        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.passWord);
        connectionProps.put("useSSL", "false");
        connectionProps.put("verifyServerCertificate", "false");
        try {

            String url = "jdbc:mysql://localhost:3306/StudentGroep";
            conn = DriverManager.getConnection(url, connectionProps);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        if (conn == null) {
            System.out.println("Warning: no database connection established!!");
        }
        return conn;
    }
}

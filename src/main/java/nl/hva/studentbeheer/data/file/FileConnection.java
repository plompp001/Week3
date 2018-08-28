/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data.file;

import nl.hva.studentbeheer.data.RepositoryConnection;


/**
 * Manage File connection.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class FileConnection extends RepositoryConnection {
    private String addressConnection;
    private String studentConnection;
    private String groupConnection;
    
    public void setAddressConnection(String addressConnection) {
        this.addressConnection = addressConnection;
    }
    
    public void setStudentConnection(String studentConnection) {
        this.studentConnection = studentConnection;
    }
    
    public void setGroupConnection(String groupConnection) {
        this.groupConnection = groupConnection;
    }
    
    public String getAddressConnection() {
        return addressConnection;
    }

    public String getStudentConnection() {
        return studentConnection;
    }

    public String getGroupConnection() {
        return groupConnection;
    }
}

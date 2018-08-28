/*
 *  Licensed HvA.
 */

package nl.hva.studentbeheer.data.db;

import java.sql.Connection;
import nl.hva.studentbeheer.data.RepositoryConnection;

/**
 * Manage DbConnection.
 * 
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class DbConnection extends RepositoryConnection {
    private final Connection connection;

    public DbConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }
    
}

/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.RepositoryConnection;
import nl.hva.studentbeheer.data.specs.ISpecification;
import nl.hva.studentbeheer.data.specs.SqlSpecification;
import nl.hva.studentbeheer.models.Address;
import java.sql.Connection;

/**
 * Manage Address objecten in DB.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class AddressDBRepository extends Repository<Address> {

    private DbConnection dbConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        dbConnection = (DbConnection) rcon;
    }

    @Override
    public void add(Address item) {
        String insertSQL = "INSERT INTO Adres"
                + "(straat, huisnr, plaats) VALUES"
                + "(?,?,?)";

        try (Connection con = dbConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(insertSQL);) {

            pstmt.setString(1, item.getStreet());
            pstmt.setInt(2, item.getHouseNumber());
            pstmt.setString(3, item.getCity());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void add(Iterable<Address> items) {
        for (Address item : items) {
            add(item);
        }
    }

    @Override
    public void update(Address item) {
        System.out.println("Update address not yet implemented!");
    }

    @Override
    public void remove(Address item) {
        System.out.println("Remove address not yet implemented!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("Remove specification not yet implemented!");
    }

    @Override
    public List<Address> query(ISpecification specification) {
        ArrayList<Address> al = new ArrayList<>();
        SqlSpecification ss = (SqlSpecification) specification;

        try (Connection con = dbConnection.getConnection(); Statement stmt = con.createStatement();) {

            Address adres;

            try (ResultSet rs = stmt.executeQuery(ss.toSqlQuery());) {
                while (rs.next()) {
                    adres = new Address(rs.getString("straat"),
                            rs.getInt("huisnr"),
                            rs.getString("plaats"));
                    System.out.println(adres);
                    al.add(adres);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return al;
    }
}

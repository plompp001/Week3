/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.db;

import java.sql.Connection;
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
import nl.hva.studentbeheer.models.Student;
import nl.hva.studentbeheer.enums.Gender;

/**
 * Manage Student objecten in DB.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class StudentDBRepository extends Repository<Student> {

    private DbConnection dbConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        dbConnection = (DbConnection) rcon;
    }

    @Override
    public void add(Student item) {

        try (Connection con = dbConnection.getConnection(); Statement stmt = con.createStatement();) {
            AddressDBRepository adb = new AddressDBRepository();
            adb.add(item.getAddress());

            int adresId = 1;
            String selectSQL = String.format(
                    "SELECT %1$s FROM %2$s WHERE %3$s = '%4$s' AND %5$s = '%6$d' AND %7$s = '%8$s';",
                    "adres_id",
                    "Adres",
                    "straat", item.getAddress().getStreet(),
                    "huisnr", item.getAddress().getHouseNumber(),
                    "plaats", item.getAddress().getCity());

            try (ResultSet rs = stmt.executeQuery(selectSQL);) {
                if (rs.next()) {
                    adresId = rs.getInt("adres_id");
                }
                String insertSQL = "INSERT INTO Student"
                        + "(voornaam, achternaam, geslacht, adres_id) VALUES"
                        + "(?,?,?,?)";

                try (PreparedStatement pstmt = con.prepareStatement(insertSQL);) {
                    pstmt.setString(1, item.getFirstname());
                    pstmt.setString(2, item.getLastname());
                    pstmt.setInt(3, item.getGender().ordinal());
                    pstmt.setInt(4, adresId);

                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void add(Iterable<Student> items) {
        for (Student item : items) {
            add(item);
        }
    }

    @Override
    public void update(Student item) {
        System.out.println("Update not yet implemented!");
    }

    @Override
    public void remove(Student item) {
        System.out.println("Remove student not yet implemented!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("Remove specification not yet implemented!");
    }

    @Override
    public List<Student> query(ISpecification specification) {
        ArrayList<Student> al = new ArrayList<>();
        SqlSpecification ss = (SqlSpecification) specification;

        try (Connection con = dbConnection.getConnection(); Statement stmt = con.createStatement();) {
            Student student;

            try (ResultSet rs = stmt.executeQuery(ss.toSqlQuery());) {
                while (rs.next()) {
                    Address adres = new Address(rs.getString("straat"),
                            rs.getInt("huisnr"),
                            rs.getString("plaats"));
                    student = new Student(rs.getString("voornaam"),
                            rs.getString("achternaam"),
                            adres,
                            (rs.getInt("geslacht") == 0) ? Gender.MAN : Gender.VROUW);
                    System.out.println(student);
                    al.add(student);
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return al;
    }
}

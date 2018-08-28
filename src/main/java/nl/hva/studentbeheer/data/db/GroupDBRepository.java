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
import nl.hva.studentbeheer.enums.Gender;
import nl.hva.studentbeheer.models.Group;
import nl.hva.studentbeheer.models.Student;

/**
 * Manage Group objecten in DB.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class GroupDBRepository extends Repository<Group> {

    private DbConnection dbConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        dbConnection = (DbConnection) rcon;
    }

    @Override
    public void add(Group item) {
        String insertSQL = "INSERT INTO Groep"
                + "(gnaam) VALUES"
                + "(?)";

        try (Connection con = dbConnection.getConnection(); PreparedStatement pstmt = con.prepareStatement(insertSQL);) {
            pstmt.setString(1, item.getName());
            pstmt.executeUpdate();

            StudentDBRepository sdb = new StudentDBRepository();
            List<Student> studenten = item.getStudents();
            for (Student student : studenten) {
                sdb.add(student);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void add(Iterable<Group> items) {
        for (Group item : items) {
            add(item);
        }
    }

    @Override
    public void update(Group item) {
        System.out.println("Update group not yet implemented!");
    }

    @Override
    public void remove(Group item) {
        System.out.println("Delete group not yet implemented!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("Delete specification not yet implemented!");
    }

    @Override
    public List<Group> query(ISpecification specification) {
        ArrayList<Group> al = new ArrayList<>();
        SqlSpecification ss = (SqlSpecification) specification;

        try (Connection con = dbConnection.getConnection(); Statement stmt = con.createStatement();) {

            Group group = null;

            try (ResultSet rs = stmt.executeQuery(ss.toSqlQuery());) {
                while (rs.next()) {

                    if (group == null || !group.getName().equals(rs.getString("gnaam"))) {
                        group = new Group(rs.getString("gnaam"));
                    }
                    Address adres = new Address(rs.getString("straat"),
                            rs.getInt("huisnr"),
                            rs.getString("plaats"));
                    Student student = new Student(rs.getString("voornaam"),
                            rs.getString("achternaam"),
                            adres,
                            (rs.getInt("geslacht") == 0) ? Gender.MAN : Gender.VROUW);
                    group.addStudent(student);
                    System.out.println(group);
                    if (!al.contains(group)) {
                        al.add(group);
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return al;
    }
}

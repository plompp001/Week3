/*
 *  Licensed HvA.
 */
package nl.hva.studentbeheer.data.file;

import java.util.ArrayList;
import java.util.List;
import nl.hva.studentbeheer.data.Repository;
import nl.hva.studentbeheer.data.RepositoryConnection;
import nl.hva.studentbeheer.data.specs.FileSpecification;
import nl.hva.studentbeheer.data.specs.ISpecification;
import nl.hva.studentbeheer.models.Student;

/**
 * Opslag van studenten in bestand.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class StudentFileRepository extends Repository<Student> {

    private FileConnection fsConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        fsConnection = (FileConnection) rcon;
    }

    @Override
    public void add(Student student) {
        System.out.println("add: Nog niet geimplementeerd!");
    }

    @Override
    public void add(Iterable<Student> studenten) {
        System.out.println("add: Nog niet geimplementeerd!");
    }

    @Override
    public void update(Student student) {
        System.out.println("update: Nog niet geimplementeerd!");
    }

    @Override
    public void remove(Student student) {
        System.out.println("delete: Nog niet geimplementeerd!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("delete: Nog niet geimplementeerd!");
    }

    @Override
    public List<Student> query(ISpecification specification) {

        ArrayList<Student> studenten = new ArrayList<>();

        if (specification instanceof FileSpecification && ((FileSpecification) specification).toFileQuery().equals("ALL")) {
            System.out.println("query: Nog niet geimplementeerd!");
        }

        return studenten;
    }
}

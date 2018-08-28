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
import nl.hva.studentbeheer.models.Group;

/**
 * Opslag van groepen in bestand.
 *
 * @author Folkert Boonstra <F.K.Boonstra@hva.nl>
 */
public class GroupFileRepository extends Repository<Group> {

    private FileConnection fsConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        fsConnection = (FileConnection) rcon;
    }

    @Override
    public void add(Group group) {
        System.out.println("add: Nog niet geimplementeerd!");
    }

    @Override
    public void add(Iterable<Group> groups) {
        System.out.println("add: Nog niet geimplementeerd!");
    }

    @Override
    public void update(Group groep) {
        System.out.println("update: Nog niet geimplementeerd!");
    }

    @Override
    public void remove(Group groep) {
        System.out.println("delete: Nog niet geimplementeerd!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("delete: Nog niet geimplementeerd!");
    }

    @Override
    public List<Group> query(ISpecification specification) {

        ArrayList<Group> groups = new ArrayList<>();

        if (specification instanceof FileSpecification && ((FileSpecification) specification).toFileQuery().equals("ALL")) {
            System.out.println("query: Nog niet geimplementeerd!");
        }

        return groups;
    }
}

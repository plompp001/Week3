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
import nl.hva.studentbeheer.models.Address;

/**
 * Opslag van adressen in bestand.
 *
 * @author Folkert Boonstra (F.K.Boonstra@hva.nl)
 */
public class AddressFileRepository extends Repository<Address> {

    private FileConnection fsConnection;

    @Override
    public void setConnection(RepositoryConnection rcon) {
        fsConnection = (FileConnection) rcon;
    }

    @Override
    public void add(Address adres) {
        System.out.println("add: Nog niet geimplementeerd!");
    }

    @Override
    public void add(Iterable<Address> adressen) {
        System.out.println("add: nog niet geimplementeerd!");
    }

    @Override
    public void update(Address adres) {
        System.out.println("update: nog niet geimplementeerd!");
    }

    @Override
    public void remove(Address adres) {
        System.out.println("delete: nog niet geimplementeerd!");
    }

    @Override
    public void remove(ISpecification specification) {
        System.out.println("delete: Nog niet geimplementeerd!");
    }

    @Override
    public List<Address> query(ISpecification specification) {

        ArrayList<Address> adressen = new ArrayList<>();

        if (specification instanceof FileSpecification && ((FileSpecification) specification).toFileQuery().equals("ALL")) {
            System.out.println("query: Nog niet geimplementeerd!");
        }
        return adressen;
    }
}

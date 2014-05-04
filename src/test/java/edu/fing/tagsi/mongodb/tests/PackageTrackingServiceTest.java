package edu.fing.tagsi.mongodb.tests;

import com.mongodb.DBCollection;
import edu.fing.tagsi.mongodb.domain.*;
import edu.fing.tagsi.mongodb.services.PackageTrackingService;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoCollectionUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/testContext.xml"})
public class PackageTrackingServiceTest {

    @Autowired
    MongoOperations operations;
    
    @Autowired
    PackageTrackingService pts;

    public String CollectionName = "packageCollection";

    @Before
    public void setUp() {
        if (operations.getCollectionNames().contains(CollectionName)) {
            operations.dropCollection(CollectionName);
        }

        String collectionName = MongoCollectionUtils.getPreferredCollectionName(PackageInfo.class);
        if (operations.getCollectionNames().contains(collectionName)) {
            operations.dropCollection(collectionName);
        }
        operations.createCollection(collectionName);
    }

    @Test
    public void createAndDropCollection() {
        assertFalse(operations.getCollectionNames().contains(CollectionName));

        /* create a new collection */
        DBCollection collection = null;
        if (!operations.getCollectionNames().contains(CollectionName)) {
            collection = operations.createCollection(CollectionName);
        }

        assertNotNull(collection);
        assertTrue(operations.getCollectionNames().contains(CollectionName));

        operations.dropCollection(CollectionName);

        assertFalse(operations.getCollectionNames().contains(CollectionName));
    }

    @Test
    public void deberiaInsertarNuevoPaquete() {
        
        PackageNode pn = new PackageNode("Maldonado", new Date("2014/3/3"), Boolean.TRUE);
        PackageInfo pi = new PackageInfo("IdPaquete03", "IdCliente03");
        pts.Add(pi, pn);

        List<PackageInfo> packages = pts.GetAllPackages();
        assertEquals(1, packages.size());
    }

    /*
    @Test
    public void createAnIndex() {
        String collectionName = MongoCollectionUtils.getPreferredCollectionName(Person.class);
        if (!operations.getCollectionNames().contains(collectionName)) {
            operations.createCollection(collectionName);
        }
        operations.ensureIndex(new Index().on("name", Order.ASCENDING), collectionName);
    }

    @Test
    public void saveAndRetrieveDocuments() {
        String collectionName = operations.getCollectionName(PackageInfo.class);

        PackageInfo p = new PackageInfo("IdPaquete01", "IdCliente01");
        operations.insert(p);

        assertEquals(1, operations.getCollection(collectionName).count());

        Person qp = operations.findOne(query(where("id").is(p.getId())), Person.class);

        assertNotNull(qp);
        assertEquals(p.getName(), qp.getName());
    }

    @Test
    public void queryingForDocuments() {
        String collectionName = MongoCollectionUtils.getPreferredCollectionName(Person.class);

        Person p1 = new Person("Bob", 33);
        p1.addAccount(new Account("198-998-2188", Account.Type.SAVINGS, 123.55d));
        operations.insert(p1);
        Person p2 = new Person("Mary", 25);
        p2.addAccount(new Account("860-98107-681", Account.Type.CHECKING, 400.51d));
        operations.insert(p2);
        Person p3 = new Person("Chris", 68);
        p3.addAccount(new Account("761-002-8901", Account.Type.SAVINGS, 10531.00d));
        operations.insert(p3);
        Person p4 = new Person("Janet", 33);
        p4.addAccount(new Account("719-100-0019", Account.Type.SAVINGS, 1209.10d));
        operations.insert(p4);

        assertEquals(4, operations.getCollection(collectionName).count());

        List<Person> result = operations.find(
        new Query(where("age").lt(50).and("accounts.balance").gt(1000.00d)),
        Person.class);

        System.out.println(result);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void updatingDocuments() {
        String collectionName = operations.getCollectionName(Person.class);

        Person p1 = new Person("Bob", 33);
        p1.addAccount(new Account("198-998-2188", Account.Type.SAVINGS, 123.55d));
        operations.insert(p1);
        Person p2 = new Person("Mary", 25);
        p2.addAccount(new Account("860-98107-681", Account.Type.CHECKING, 400.51d));
        operations.insert(p2);
        Person p3 = new Person("Chris", 68);
        p3.addAccount(new Account("761-002-8901", Account.Type.SAVINGS, 10531.00d));
        operations.insert(p3);
        Person p4 = new Person("Janet", 33);
        p4.addAccount(new Account("719-100-0019", Account.Type.SAVINGS, 1209.10d));
        operations.insert(p4);

        assertEquals(4, operations.getCollection(collectionName).count());

        WriteResult wr = operations.updateMulti(
        query(where("accounts.accountType").is(Account.Type.SAVINGS.name())),
        new Update().inc("accounts.$.balance", 50.00),
        Person.class);

        assertNotNull(wr);
        assertEquals(3, wr.getN());
    }
    */

}
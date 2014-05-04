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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

        int before = operations.find(new Query(), PackageInfo.class).size();
        PackageNode pn = new PackageNode("Maldonado", new Date("2014/3/3"), Boolean.TRUE);
        PackageInfo pi = new PackageInfo("IdPaquete01", "IdCliente01");

        pts.Add(pi, pn);

        int after = operations.find(new Query(), PackageInfo.class).size();
        assertEquals(before + 1, after);

        operations.remove(pi);
    }

    @Test
    public void deberiaInsertarNuevoNodo() {

        PackageInfo pi = new PackageInfo("IdPaquete02", "IdCliente02");
        PackageNode pnode = new PackageNode("Maldonado", new Date("2014/3/3"), Boolean.FALSE);
        pi.addNode(pnode);
        operations.save(pi);
        Query q = new Query().addCriteria(Criteria.where("_id").is(pi.getIdPaquete()).and("idCliente").is(pi.getIdCliente()));

        int beforeNodes = operations.find(q, PackageInfo.class).get(0).getNodes().size();
        int before = operations.find(new Query(), PackageInfo.class).size();

        PackageInfo pi0 = new PackageInfo("IdPaquete02", "IdCliente02");
        PackageNode pnode0 = new PackageNode("Artigas", new Date("2014/5/5"), Boolean.TRUE);
        pts.Add(pi0, pnode0);
        List<PackageNode> packs = operations.find(q, PackageInfo.class).get(0).getNodes();

        int afterNodes = packs.size();
        int after = operations.find(new Query(), PackageInfo.class).size();

        //No se agrega paquete, se actualiza el que ya estaba
        assertEquals(before, after);
        //Check nodos
        assertEquals(before, after);
        assertEquals(new Date("2014/3/3"), packs.get(0).getDate());
        assertEquals(new Date("2014/5/5"), packs.get(1).getDate());
        assertEquals("Maldonado", packs.get(0).getIdLugar());
        assertEquals("Artigas", packs.get(1).getIdLugar());
        assertFalse(packs.get(0).isDestination());
        assertTrue(packs.get(1).isDestination());

        operations.remove(pi);
    }

    @Test
    public void deberiaObtenerTracking() {

        PackageInfo pi = new PackageInfo("IdPaqueteTracking", "IdClienteTracking");
        PackageNode pn = new PackageNode("Montevideo", new Date("2014/1/1"), Boolean.FALSE);
        pi.addNode(pn);
        pn = new PackageNode("SanJose", new Date("2014/1/2"), Boolean.FALSE);
        pi.addNode(pn);
        pn = new PackageNode("Flores", new Date("2014/1/3"), Boolean.FALSE);
        pi.addNode(pn);
        pn = new PackageNode("Paysandu", new Date("2014/1/4"), Boolean.FALSE);
        pi.addNode(pn);
        pn = new PackageNode("Salto", new Date("2014/1/5"), Boolean.TRUE);
        pi.addNode(pn);
        operations.save(pi);

        List<PackageNode> packNodes = pts.GetTracking(pi.getIdPaquete()).getNodes();

        //Check number of nodes
        assertEquals(5, packNodes.size());
        //Check 1st idLugar
        assertEquals("Montevideo", packNodes.get(0).getIdLugar());
        //Check 2nd Date
        assertEquals(new Date("2014/1/2"), packNodes.get(1).getDate());
        //Check 4th and 5th destination property
        assertFalse(packNodes.get(3).isDestination());
        assertTrue(packNodes.get(4).isDestination());

        operations.remove(pi);
    }
}

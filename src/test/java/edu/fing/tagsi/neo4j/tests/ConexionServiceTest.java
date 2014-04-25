package edu.fing.tagsi.neo4j.tests;
/*
import edu.fing.tasig.neo4j.services.CiudadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fing.tasig.neo4j.domain.Ciudad;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@ContextConfiguration(locations = "classpath:/spring/helloWorldContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional*/
public class ConexionServiceTest {
/*
	@Autowired
	private CiudadService ciudadService;
	
	@Autowired
	private Neo4jTemplate template;
	
	@Rollback(false)
	@BeforeTransaction
	public void cleanUpGraph() {
		Neo4jHelper.cleanDb(template);
	}
	
	@Test
    public void shouldAllowDirectWorldCreation() {
		assertEquals(0, ciudadService.getCantidadCiudades());
		Ciudad myWorld = ciudadService.crearCiudad("mine");
        assertEquals(1, ciudadService.getCantidadCiudades());
        
        Iterable<Ciudad> foundWorlds = ciudadService.getTodasLasCiudades();
        Ciudad mine = foundWorlds.iterator().next();
        assertEquals(myWorld.getName(), mine.getName());
    }
	
	@Test
    public void shouldHaveCorrectNumberOfWorlds() {
		ciudadService.crearAlgunasCiudades();
        assertEquals(4, ciudadService.getCantidadCiudades());
    }

    @Test
    public void shouldFindWorldsById() {
    	ciudadService.crearAlgunasCiudades();
    	
        for(Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
        	Ciudad foundWorld = ciudadService.findCiudadById(ciudad.getId());
            assertNotNull(foundWorld);
        }
    }
    
    @Test
    public void shouldFindWorldsByName() {
    	ciudadService.crearAlgunasCiudades();
    	
        for(Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
        	Ciudad foundWorld = ciudadService.findCiudadByName(ciudad.getName()); 
            assertNotNull(foundWorld);
        }
    }
    
    @Test
    public void shouldReachMarsFromEarth() {
        ciudadService.crearAlgunasCiudades();

        Ciudad tacuarembo = ciudadService.findCiudadByName("Tacuarembo");
        Ciudad rivera = ciudadService.findCiudadByName("Rivera");

        assertTrue(tacuarembo.seLlegaDesde(rivera));
        assertTrue(rivera.seLlegaDesde(tacuarembo));
    }
    
    @Test
    public void shouldFindAllWorlds() {
        Collection<Ciudad> madeWorlds = ciudadService.crearAlgunasCiudades();
        Iterable<Ciudad> foundWorlds = ciudadService.getTodasLasCiudades();
        
        int totalCiudadesEncontradas = 0;
        for(Ciudad ciudadEncontrada : foundWorlds) {
            assertTrue(madeWorlds.contains(ciudadEncontrada));
            totalCiudadesEncontradas++;
        }

        assertEquals(madeWorlds.size(), totalCiudadesEncontradas);
    }
    
*/	
}

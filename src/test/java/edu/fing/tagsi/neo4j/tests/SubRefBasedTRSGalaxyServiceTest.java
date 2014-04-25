package edu.fing.tagsi.neo4j.tests;

import edu.fing.tagsi.neo4j.services.CiudadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import edu.fing.tagsi.neo4j.domain.Ciudad;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/*
@ContextConfiguration(locations = "classpath:/spring/helloWorldContext-subRef-TRS.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = false)
* */
public class SubRefBasedTRSGalaxyServiceTest {
	/*
	@Autowired
	private CiudadService galaxyService;
	
	@Autowired
	private Neo4jTemplate template;
	
	@Rollback(false)
	@BeforeTransaction
	public void cleanUpGraph() {
		Neo4jHelper.cleanDb(template);
	}
	
	@Test
    public void shouldAllowDirectWorldCreation() {
		assertEquals(0, galaxyService.getCantidadCiudades());
		Ciudad myWorld = galaxyService.createWorld("mine", 0);
        assertEquals(1, galaxyService.getCantidadCiudades());
        
        Iterable<Ciudad> foundWorlds = galaxyService.getAllWorlds();
        Ciudad mine = foundWorlds.iterator().next();
        assertEquals(myWorld.getName(), mine.getName());
    }
	
	@Test
    public void shouldHaveCorrectNumberOfWorlds() {
		galaxyService.crearAlgunasCiudades();
        assertEquals(13, galaxyService.getCantidadCiudades());
    }

    @Test
    public void shouldFindWorldsById() {
    	galaxyService.crearAlgunasCiudades();
    	
        for(Ciudad world : galaxyService.getAllWorlds()) {
        	Ciudad foundWorld = galaxyService.findWorldById(world.getId()); 
            assertNotNull(foundWorld);
        }
    }
    
    @Test
    public void shouldFindWorldsByName() {
    	galaxyService.crearAlgunasCiudades();
    	
        for(Ciudad world : galaxyService.getAllWorlds()) {
        	Ciudad foundWorld = galaxyService.findCiudadByName(world.getName()); 
            assertNotNull(foundWorld);
        }
    }
    
    @Test
    public void shouldReachMarsFromEarth() {
        galaxyService.crearAlgunasCiudades();

        Ciudad earth = galaxyService.findCiudadByName("Earth");
        Ciudad mars = galaxyService.findCiudadByName("Mars");

        assertTrue(mars.canBeReachedFrom(earth));
        assertTrue(earth.canBeReachedFrom(mars));
    }
    
    @Test
    public void shouldFindAllWorlds() {
        Collection<Ciudad> madeWorlds = galaxyService.crearAlgunasCiudades();
        Iterable<Ciudad> foundWorlds = galaxyService.getAllWorlds();

        int countOfFoundWorlds = 0;
        for(Ciudad foundWorld : foundWorlds) {
            assertTrue(madeWorlds.contains(foundWorld));
            countOfFoundWorlds++;
        }

        assertEquals(madeWorlds.size(), countOfFoundWorlds);
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void shouldFindWorldsWith1Moon() {
        galaxyService.crearAlgunasCiudades();
        
        for(Ciudad worldWithOneMoon : galaxyService.findAllByNumberOfMoons(1)) {
        	assertThat(
        			worldWithOneMoon.getName(), 
        			is(anyOf(containsString("Earth"), containsString("Midgard"))));
        }
    }
		
	@Test
	public void shouldNotFindKrypton() {
		galaxyService.crearAlgunasCiudades();
		Ciudad krypton = galaxyService.findCiudadByName("Krypton");
		assertNull(krypton);
	}*/
	
}

package edu.fing.tagsi.neo4j.tests;

import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.services.CiudadService;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.data.neo4j.support.node.Neo4jHelper;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:/test/testContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CiudadServiceTest {

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
  public void deberiaAceptarCreacionDirecta() {
    Assert.assertEquals(0, ciudadService.getCantidadCiudades());
    Ciudad myWorld = ciudadService.crearCiudad("mine");
    Assert.assertEquals(1, ciudadService.getCantidadCiudades());

    Iterable<Ciudad> foundWorlds = ciudadService.getTodasLasCiudades();
    Ciudad mine = foundWorlds.iterator().next();
    Assert.assertEquals(myWorld.getName(), mine.getName());
  }

  @Test
  public void deberiaDarCantidadDeCiudadesCorrecta() {
    ciudadService.crearAlgunasCiudades();
    Assert.assertEquals(4, ciudadService.getCantidadCiudades());
  }

  @Test
  public void deberiaEncontrarCiudadesPorId() {
    ciudadService.crearAlgunasCiudades();

    for (Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
      Ciudad foundWorld = ciudadService.findCiudadById(ciudad.getId());
      Assert.assertNotNull(foundWorld);
    }
  }

  @Test
  public void deberiaEncontrarCiudadesPorNombre() {
    ciudadService.crearAlgunasCiudades();

    for (Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
      Ciudad foundWorld = ciudadService.findCiudadByName(ciudad.getName());
      Assert.assertNotNull(foundWorld);
    }
  }

  @Test
  public void deberiaLlegarATacuaremboDesdeRivera() {
    ciudadService.crearAlgunasCiudades();

    Ciudad tacuarembo = ciudadService.findCiudadByName("Tacuarembo");
    Ciudad rivera = ciudadService.findCiudadByName("Rivera");

    Assert.assertTrue(tacuarembo.seLlegaDesde(rivera));
    Assert.assertTrue(rivera.seLlegaDesde(tacuarembo));
  }

  @Test
  public void deberiaEncontrarTodasLasCiudades() {
    Collection<Ciudad> madeWorlds = ciudadService.crearAlgunasCiudades();
    Iterable<Ciudad> foundWorlds = ciudadService.getTodasLasCiudades();

    int totalCiudadesEncontradas = 0;
    for (Ciudad ciudadEncontrada : foundWorlds) {
      Assert.assertTrue(madeWorlds.contains(ciudadEncontrada));
      totalCiudadesEncontradas++;
    }

    Assert.assertEquals(madeWorlds.size(), totalCiudadesEncontradas);
  }
}

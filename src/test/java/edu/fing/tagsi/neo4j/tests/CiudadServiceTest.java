package edu.fing.tagsi.neo4j.tests;

import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.domain.Ruta;
import edu.fing.tagsi.neo4j.services.CiudadService;
import edu.fing.tagsi.neo4j.services.RutaService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
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
  private RutaService rutaService;
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
    crearAlgunasCiudades();
    Assert.assertEquals(5, ciudadService.getCantidadCiudades());
  }

  @Test
  public void deberiaEncontrarCiudadesPorId() {
    crearAlgunasCiudades();

    for (Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
      Ciudad foundWorld = ciudadService.findCiudadById(ciudad.getId());
      Assert.assertNotNull(foundWorld);
    }
  }

  @Test
  public void deberiaEncontrarCiudadesPorNombre() {
    crearAlgunasCiudades();

    for (Ciudad ciudad : ciudadService.getTodasLasCiudades()) {
      Ciudad foundWorld = ciudadService.findCiudadByName(ciudad.getName());
      Assert.assertNotNull(foundWorld);
    }
  }

  @Test
  public void buscarCaminoRiveraMontevideo() {
    crearAlgunasCiudades();

    Ciudad rivera = ciudadService.findCiudadByName("Rivera");
    Ciudad montevideo = ciudadService.findCiudadByName("Montevideo");
    
    Object[][] rutasEsperadas = {{"Rivera", "Tacuarembo", 5, 120.0},
      {"Tacuarembo", "Canelones", 5, 330.0},
      {"Canelones", "Montevideo", 5, 50.0},};
    
    Object[] ciudadesEsperadas = {"Rivera", "Tacuarembo", "Canelones", "Montevideo"};
    
    WeightedPath camino = ciudadService.caminoMasCorto(rivera, montevideo);
    
    chequearCamino(rutasEsperadas, ciudadesEsperadas, camino, 500.0);

    
  }

  @Test
  public void buscarCaminoRiveraMontevideoConShortcut() {
    crearAlgunasCiudadesConShortcutEfectivo();
    Ciudad rivera = ciudadService.findCiudadByName("Rivera");
    Ciudad montevideo = ciudadService.findCiudadByName("Montevideo");

    Object[][] rutasEsperadas = {{"Rivera", "Shortcut", 7, 469.0},
      {"Shortcut", "Montevideo", 7, 30.0},};
    
    Object[] ciudadesEsperadas = {"Rivera", "Shortcut", "Montevideo"};
    
    WeightedPath caminoEncontrado = ciudadService.caminoMasCorto(rivera, montevideo);

    chequearCamino(rutasEsperadas, ciudadesEsperadas, caminoEncontrado, 499.0);



  }

  @Test
  public void deberiaEncontrarTodasLasCiudades() {
    Collection<Ciudad> madeWorlds = crearAlgunasCiudades();
    Iterable<Ciudad> foundWorlds = ciudadService.getTodasLasCiudades();

    int totalCiudadesEncontradas = 0;
    for (Ciudad ciudadEncontrada : foundWorlds) {
      Assert.assertTrue(madeWorlds.contains(ciudadEncontrada));
      totalCiudadesEncontradas++;
    }

    Assert.assertEquals(madeWorlds.size(), totalCiudadesEncontradas);
  }

  private Collection<Ciudad> crearAlgunasCiudades() {
    Collection<Ciudad> ciudades = new ArrayList<Ciudad>();
    Ciudad montevideo = ciudadService.crearCiudad("Montevideo");
    Ciudad canelones = ciudadService.crearCiudad("Canelones");


    Ciudad rivera = ciudadService.crearCiudad("Rivera");
    Ciudad tacuarembo = ciudadService.crearCiudad("Tacuarembo");
    ciudades.add(montevideo);
    ciudades.add(canelones);
    ciudades.add(tacuarembo);
    ciudades.add(rivera);
    Ciudad shortcut = ciudadService.crearCiudad("Shortcut");


    Ruta ruta = new Ruta(rivera, tacuarembo, Arrays.asList(5), 120);
    rivera.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(rivera);
    ruta = new Ruta(tacuarembo, canelones, Arrays.asList(5), 330);
    tacuarembo.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(tacuarembo);
    ruta = new Ruta(canelones, montevideo, Arrays.asList(5), 50);
    canelones.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(canelones);

    ruta = new Ruta(rivera, shortcut, Arrays.asList(7), 471);
    rivera.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(rivera);
    ruta = new Ruta(shortcut, montevideo, Arrays.asList(7), 30);
    shortcut.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(shortcut);
    ciudades.add(shortcut);
    return ciudades;
  }

  private Collection<Ciudad> crearAlgunasCiudadesConShortcutEfectivo() {
    Collection<Ciudad> ciudades = new ArrayList<Ciudad>();
    Ciudad montevideo = ciudadService.crearCiudad("Montevideo");
    Ciudad canelones = ciudadService.crearCiudad("Canelones");


    Ciudad rivera = ciudadService.crearCiudad("Rivera");
    Ciudad tacuarembo = ciudadService.crearCiudad("Tacuarembo");
    ciudades.add(montevideo);
    ciudades.add(canelones);
    ciudades.add(tacuarembo);
    ciudades.add(rivera);
    Ciudad shortcut = ciudadService.crearCiudad("Shortcut");


    Ruta ruta = new Ruta(rivera, tacuarembo, Arrays.asList(5), 120);
    rivera.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(rivera);
    ruta = new Ruta(tacuarembo, canelones, Arrays.asList(5), 330);
    tacuarembo.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(tacuarembo);
    ruta = new Ruta(canelones, montevideo, Arrays.asList(5), 50);
    canelones.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(canelones);

    ruta = new Ruta(rivera, shortcut, Arrays.asList(7), 469);
    rivera.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(rivera);
    ruta = new Ruta(shortcut, montevideo, Arrays.asList(7), 30);
    shortcut.agregarRuta(ruta);
    ciudadService.crearOActualizarCiudad(shortcut);
    ciudades.add(shortcut);
    return ciudades;
  }

  private void chequearCamino(Object[][] rutasEsperadas,
          Object[] ciudadesEsperadas, WeightedPath camino, double distanciaTotal) {
    int i = 0;
    Assert.assertNotNull(camino);
    Assert.assertEquals(distanciaTotal, camino.weight(),0.0);
    for (Relationship r : camino.relationships()) {
      Ruta ruta = rutaService.findRutaById(r.getId());
      Ciudad ciudadOrigen = ciudadService.findCiudadById(r.getStartNode().getId());
      Ciudad ciudadDestino = ciudadService.findCiudadById(r.getEndNode().getId());
      Assert.assertEquals(rutasEsperadas[i][0], ciudadOrigen.getName());
      Assert.assertEquals(rutasEsperadas[i][1], ciudadDestino.getName());
      Assert.assertEquals(rutasEsperadas[i][2], ruta.getRutas().get(0));
      Assert.assertEquals(rutasEsperadas[i][3], ruta.getDistancia());
      i++;
    }
    i = 0;
    for (Node n : camino.nodes()) {
      Ciudad ciudad = ciudadService.findCiudadById(n.getId());
      Assert.assertEquals(ciudadesEsperadas[i], ciudad.getName());
      i++;
    }
  }
}

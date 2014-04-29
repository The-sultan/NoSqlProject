package edu.fing.tagsi.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.domain.Ruta;
import edu.fing.tagsi.neo4j.repositories.CiudadRepository;
import edu.fing.tagsi.neo4j.repositories.RutaRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.PostConstruct;
import org.neo4j.graphalgo.CostEvaluator;
import org.neo4j.graphalgo.GraphAlgoFactory;
import org.neo4j.graphalgo.PathFinder;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.PathExpander;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.traversal.BranchState;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CiudadService {
  
  
  @PostConstruct
  public void init(){
    this.crearAlgunasCiudades();
  }

  @Autowired
  private CiudadRepository ciudadRepository;
  
  @Autowired
  private RutaRepository rutaRepository;

  @Autowired
  private Neo4jTemplate template;
  public long getCantidadCiudades() {
    return ciudadRepository.count();
  }

  public Ciudad guardarCiudad(Ciudad ciudad) {
    Ciudad savedCity = ciudadRepository.save(ciudad);
    for(Ruta ruta : ciudad.getRutas()){
      rutaRepository.save(ruta);
    }
    return savedCity;
  }

  public Ciudad crearCiudad(String name) {
    return ciudadRepository.save(new Ciudad(name));
  }
  
  public Ciudad crearOActualizarCiudad(Ciudad ciudad){
    return ciudadRepository.save(ciudad);
  }

  public Iterable<Ciudad> getTodasLasCiudades() {
    return ciudadRepository.findAll();
  }

  public Ciudad findCiudadById(Long id) {
    return ciudadRepository.findOne(id);
  }

  // This is using the schema based index
  public Ciudad findCiudadByName(String name) {
    return ciudadRepository.findBySchemaPropertyValue("name", name);
  }

  public WeightedPath caminoMasCorto(Ciudad origen, Ciudad destino) {

    if (template != null) {

      
      PathFinder<WeightedPath> pathFinder = GraphAlgoFactory.dijkstra(
              new CiudadService.RutasExpander(), 
              new CiudadService.CalculadorCostoRutas());
      
      Node nodoOrigen = template.getNode(origen.getId());
      Node nodoDestino = template.getNode(destino.getId());
      return pathFinder.findSinglePath(nodoOrigen, nodoDestino);
      
    }else{
      return null;
    }
  }
  
  private static class RutasExpander implements PathExpander<WeightedPath>{

    @Override
    public Iterable expand(Path path, BranchState bs) {
      return path.endNode().getRelationships(Direction.OUTGOING, 
              DynamicRelationshipType.withName(Ruta.RUTA));
    }

    @Override
    public PathExpander reverse() {
      return null;
    }
  
  }
  
  private static class CalculadorCostoRutas implements CostEvaluator{

    @Override
    public Object getCost(Relationship r, Direction drctn) {
      return r.getProperty("distancia");
    }
    
  }
  
   private Collection<Ciudad> crearAlgunasCiudades() {
    Collection<Ciudad> ciudades = new ArrayList<Ciudad>();
    Ciudad montevideo = this.crearCiudad("Montevideo");
    Ciudad canelones = this.crearCiudad("Canelones");


    Ciudad rivera = this.crearCiudad("Rivera");
    Ciudad tacuarembo = this.crearCiudad("Tacuarembo");
    ciudades.add(montevideo);
    ciudades.add(canelones);
    ciudades.add(tacuarembo);
    ciudades.add(rivera);
    Ciudad shortcut = this.crearCiudad("Shortcut");


    Ruta ruta = new Ruta(rivera, tacuarembo, Arrays.asList(5), 120);
    rivera.agregarRuta(ruta);
    this.crearOActualizarCiudad(rivera);
    ruta = new Ruta(tacuarembo, canelones, Arrays.asList(5), 330);
    tacuarembo.agregarRuta(ruta);
    this.crearOActualizarCiudad(tacuarembo);
    ruta = new Ruta(canelones, montevideo, Arrays.asList(5), 50);
    canelones.agregarRuta(ruta);
    this.crearOActualizarCiudad(canelones);

    ruta = new Ruta(rivera, shortcut, Arrays.asList(7), 471);
    rivera.agregarRuta(ruta);
    this.crearOActualizarCiudad(rivera);
    ruta = new Ruta(shortcut, montevideo, Arrays.asList(7), 30);
    shortcut.agregarRuta(ruta);
    this.crearOActualizarCiudad(shortcut);
    ciudades.add(shortcut);
    return ciudades;
  }
}



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
    Ciudad colonia = this.crearCiudad("Colonia");
    Ciudad sanjose = this.crearCiudad("San Jose");
    Ciudad maldonado = this.crearCiudad("Maldonado");
    Ciudad rocha = this.crearCiudad("Rocha");
    Ciudad mercedes = this.crearCiudad("Mercedes");
    Ciudad trinidad = this.crearCiudad("Trinidad");
    Ciudad florida = this.crearCiudad("Florida");
    Ciudad minas = this.crearCiudad("Minas");
    Ciudad fraybentos = this.crearCiudad("Fray Bentos");
    Ciudad durazno = this.crearCiudad("Durazno");
    Ciudad treintaytres = this.crearCiudad("Treinta y Tres");
    Ciudad paysandu = this.crearCiudad("Paysandu");
    Ciudad melo = this.crearCiudad("Melo");
    Ciudad salto = this.crearCiudad("Salto");
    Ciudad artigas = this.crearCiudad("Artigas");
    
    ciudades.add(montevideo);
    ciudades.add(canelones);
    ciudades.add(tacuarembo);
    ciudades.add(rivera);
    ciudades.add(colonia);
    ciudades.add(sanjose);
    ciudades.add(maldonado);
    ciudades.add(rocha);
    ciudades.add(mercedes);
    ciudades.add(trinidad);
    ciudades.add(florida);
    ciudades.add(minas);
    ciudades.add(fraybentos);
    ciudades.add(durazno);
    ciudades.add(treintaytres);
    ciudades.add(paysandu);
    ciudades.add(melo);
    ciudades.add(salto);
    ciudades.add(artigas);

    
    
    //Montevideo
    Ruta ruta = new Ruta(montevideo, colonia, Arrays.asList(1), 177);
    montevideo.agregarRuta(ruta);
    ruta = new Ruta(montevideo, sanjose, Arrays.asList(1,3), 93);
    montevideo.agregarRuta(ruta);
    ruta = new Ruta(montevideo, canelones, Arrays.asList(5), 46);
    montevideo.agregarRuta(ruta);
    ruta = new Ruta(montevideo, maldonado, Arrays.asList(1), 134);
    montevideo.agregarRuta(ruta);
    ruta = new Ruta(montevideo, minas, Arrays.asList(1,8), 122);
    montevideo.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(montevideo);   
    
    //Colonia
    ruta = new Ruta(colonia, sanjose, Arrays.asList(11,1), 108);
    colonia.agregarRuta(ruta);
    ruta = new Ruta(colonia, mercedes, Arrays.asList(21), 176);
    colonia.agregarRuta(ruta);
    ruta = new Ruta(colonia, montevideo, Arrays.asList(1), 177);
    colonia.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(colonia);
    
    //Mercedes
    ruta = new Ruta(mercedes, colonia, Arrays.asList(21), 176);
    mercedes.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(mercedes);
    
    //San Jose
    ruta = new Ruta(sanjose, trinidad, Arrays.asList(3), 95);
    sanjose.agregarRuta(ruta);
    ruta = new Ruta(sanjose, florida, Arrays.asList(5,11), 98);
    sanjose.agregarRuta(ruta);
    ruta = new Ruta(sanjose, canelones, Arrays.asList(11), 47);
    sanjose.agregarRuta(ruta);
    ruta = new Ruta(sanjose, montevideo, Arrays.asList(1,3), 93);
    sanjose.agregarRuta(ruta);
    ruta = new Ruta(sanjose, colonia, Arrays.asList(1,11), 108);
    sanjose.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(sanjose);
    
    //Canelones
    ruta = new Ruta(canelones, florida, Arrays.asList(5), 52);
    canelones.agregarRuta(ruta);
    ruta = new Ruta(canelones, maldonado, Arrays.asList(8,1), 155);
    canelones.agregarRuta(ruta);
    ruta = new Ruta(canelones, trinidad, Arrays.asList(5,11), 98);
    canelones.agregarRuta(ruta);
    ruta = new Ruta(canelones, montevideo, Arrays.asList(5), 46);
    canelones.agregarRuta(ruta);
    ruta = new Ruta(canelones, sanjose, Arrays.asList(11), 47);
    canelones.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(canelones);
    
    //Maldonado
    ruta = new Ruta(maldonado, rocha, Arrays.asList(9), 85);
    maldonado.agregarRuta(ruta);
    ruta = new Ruta(maldonado, montevideo, Arrays.asList(1), 134);
    maldonado.agregarRuta(ruta);
    ruta = new Ruta(maldonado, canelones, Arrays.asList(1,8), 155);
    maldonado.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(maldonado);
    
    
    //Rocha
    ruta = new Ruta(rocha, maldonado, Arrays.asList(9), 85);
    rocha.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(rocha);
    
    //Mercedes
    ruta = new Ruta(mercedes, fraybentos, Arrays.asList(2), 31);
    mercedes.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(mercedes);
    
    //Fray Bentos
    ruta = new Ruta(fraybentos, mercedes, Arrays.asList(2), 31);
    fraybentos.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(fraybentos);
    
    //Trinidad
    ruta = new Ruta(trinidad, durazno, Arrays.asList(14), 41);
    trinidad.agregarRuta(ruta);
    ruta = new Ruta(trinidad, paysandu, Arrays.asList(3), 190);
    trinidad.agregarRuta(ruta);
    ruta = new Ruta(trinidad, sanjose, Arrays.asList(3), 95);
    trinidad.agregarRuta(ruta);
    ruta = new Ruta(trinidad, canelones, Arrays.asList(11,5), 98);
    trinidad.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(trinidad);
    
    //Florida
    ruta = new Ruta(florida, durazno, Arrays.asList(5), 85);
    florida.agregarRuta(ruta);
    ruta = new Ruta(florida, sanjose, Arrays.asList(11,5), 98);
    florida.agregarRuta(ruta);
    ruta = new Ruta(florida, canelones, Arrays.asList(5), 52);
    florida.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(florida);
    
    //Minas
    ruta = new Ruta(minas, treintaytres, Arrays.asList(8), 164);
    minas.agregarRuta(ruta);
    ruta = new Ruta(minas, montevideo, Arrays.asList(8,1), 122);
    minas.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(minas);
    
    //Durazno
    ruta = new Ruta(durazno, tacuarembo, Arrays.asList(5), 207);
    durazno.agregarRuta(ruta);
    ruta = new Ruta(durazno, trinidad, Arrays.asList(14), 41);
    durazno.agregarRuta(ruta);
    ruta = new Ruta(durazno, florida, Arrays.asList(5), 85);
    durazno.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(durazno);
    
    //Treinta y Tres
    ruta = new Ruta(treintaytres, melo, Arrays.asList(8), 113);
    treintaytres.agregarRuta(ruta);
    ruta = new Ruta(treintaytres, minas, Arrays.asList(8), 164);
    treintaytres.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(treintaytres);
    
    //Melo
    ruta = new Ruta(melo, treintaytres, Arrays.asList(8), 113);
    melo.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(melo);
    //Paysandu
    ruta = new Ruta(paysandu, salto, Arrays.asList(3), 118);
    paysandu.agregarRuta(ruta);
    ruta = new Ruta(paysandu, trinidad, Arrays.asList(3), 190);
    paysandu.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(paysandu);
    
    //Tacuarembo
    ruta = new Ruta(tacuarembo, rivera, Arrays.asList(5), 111);
    tacuarembo.agregarRuta(ruta);
    ruta = new Ruta(tacuarembo, durazno, Arrays.asList(5), 207);
    tacuarembo.agregarRuta(ruta);
    ruta = new Ruta(tacuarembo, artigas, Arrays.asList(5,30), 211);
    tacuarembo.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(tacuarembo);
    
    //Salto
    ruta = new Ruta(salto, artigas, Arrays.asList(30, 3), 207);
    salto.agregarRuta(ruta);
    ruta = new Ruta(salto, paysandu, Arrays.asList(3), 118);
    salto.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(salto);
    
    //Rivera
    ruta = new Ruta(rivera, artigas, Arrays.asList(5,30), 183);
    rivera.agregarRuta(ruta);
    ruta = new Ruta(rivera, tacuarembo, Arrays.asList(5), 111);
    rivera.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(rivera);
    
    //Artigas
    ruta = new Ruta(artigas, tacuarembo, Arrays.asList(5,30), 211);
    artigas.agregarRuta(ruta);
    ruta = new Ruta(artigas, salto, Arrays.asList(3, 30), 207);
    artigas.agregarRuta(ruta);
    ruta = new Ruta(artigas, rivera, Arrays.asList(5,30), 183);
    artigas.agregarRuta(ruta);
    
    this.crearOActualizarCiudad(artigas);

    return ciudades;
  }
}



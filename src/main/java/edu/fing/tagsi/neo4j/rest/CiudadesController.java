package edu.fing.tagsi.neo4j.rest;


import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.rest.transfer.CiudadDTO;
import edu.fing.tagsi.neo4j.rest.transfer.MejorCaminoRequest;
import edu.fing.tagsi.neo4j.rest.transfer.MejorCaminoResponse;
import edu.fing.tagsi.neo4j.rest.transfer.RutaDTO;
import edu.fing.tagsi.neo4j.services.CiudadService;
import edu.fing.tagsi.neo4j.services.RutaService;
import java.util.ArrayList;
import java.util.List;
import org.neo4j.graphalgo.WeightedPath;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Farid
 */

@Controller
public class CiudadesController {

  @Autowired
  private CiudadService ciudadService;
  @Autowired
  private RutaService rutasService;

  @RequestMapping(value = "/mejorCamino", method = RequestMethod.POST)
  public @ResponseBody MejorCaminoResponse mejorCamino(@RequestBody MejorCaminoRequest mcr) {
    edu.fing.tagsi.neo4j.domain.Ciudad ciudadOrigen = ciudadService.findCiudadByName(mcr.getCiudadOrigen());
    edu.fing.tagsi.neo4j.domain.Ciudad ciudadDestino = ciudadService.findCiudadByName(mcr.getCiudadDestino());
    if(ciudadOrigen == null || ciudadDestino == null)
      return new MejorCaminoResponse();
    WeightedPath camino = ciudadService.caminoMasCorto(ciudadOrigen, ciudadDestino);
    if(camino == null){
      return new MejorCaminoResponse();
    }
    
      
    List<CiudadDTO> ciudades = new ArrayList<CiudadDTO>();
    List<RutaDTO> rutas = new ArrayList<RutaDTO>();
    for (Node nodo : camino.nodes()) {
      Ciudad ciudad = ciudadService.findCiudadById(nodo.getId());
      ciudades.add(new CiudadDTO(ciudad));
    }
    for (Relationship r : camino.relationships()) {
      rutas.add(new RutaDTO(rutasService.findRutaById(r.getId())));
    }
    return new MejorCaminoResponse(rutas, ciudades, camino.weight());
  }
}
 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.rest.transfer;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Farid
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MejorCaminoResponse implements Serializable{
  Double distanciaTotal;
  List<CiudadDTO> ciudades;
  List<RutaDTO> rutas;
  
  public MejorCaminoResponse(List<RutaDTO> rutas, List<CiudadDTO> ciudades, Double distanciaTotal){
    this.rutas = rutas;
    this.ciudades = ciudades;
    this.distanciaTotal = distanciaTotal;
  }

  public MejorCaminoResponse() {
  }

  public Double getDistanciaTotal() {
    return distanciaTotal;
  }

  public void setDistanciaTotal(Double distanciaTotal) {
    this.distanciaTotal = distanciaTotal;
  }
  
}

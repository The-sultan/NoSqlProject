/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.rest.transfer;

import edu.fing.tagsi.neo4j.domain.Ruta;
import java.util.List;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Farid
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RutaDTO {

  public RutaDTO() {
  }
  
  public RutaDTO(Ruta ruta){
    this.distancia = ruta.getDistancia();
    this.rutas = ruta.getRutas();
  }
  
  private double distancia;
  
  private List<Integer> rutas;

  public RutaDTO(double distancia, List<Integer> rutas) {
    this.distancia = distancia;
    this.rutas = rutas;
  }

  public double getDistancia() {
    return distancia;
  }

  public void setDistancia(double distancia) {
    this.distancia = distancia;
  }

  public List<Integer> getRutas() {
    return rutas;
  }

  public void setRutas(List<Integer> rutas) {
    this.rutas = rutas;
  }
  
  
}

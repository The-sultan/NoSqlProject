/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.rest.transfer;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Farid
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class MejorCaminoRequest implements Serializable{
  private String ciudadOrigen;
  private String ciudadDestino;

  public MejorCaminoRequest() {
  }

  public String getCiudadOrigen() {
    return ciudadOrigen;
  }

  public void setCiudadOrigen(String ciudadOrigen) {
    this.ciudadOrigen = ciudadOrigen;
  }

  public String getCiudadDestino() {
    return ciudadDestino;
  }

  public void setCiudadDestino(String ciudadDestino) {
    this.ciudadDestino = ciudadDestino;
  }
  
  
}

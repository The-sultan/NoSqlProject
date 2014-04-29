/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.rest.transfer;

import edu.fing.tagsi.neo4j.domain.Ciudad;
import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 *
 * @author Farid
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CiudadDTO {
  
  private String name;

  public CiudadDTO(String name) {
    this.name = name;
  }
  
  public CiudadDTO(Ciudad ciudad){
    this.name = ciudad.getName();
  }

  public CiudadDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}

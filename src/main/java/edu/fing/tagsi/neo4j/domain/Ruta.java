/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.domain;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 *
 * @author Farid
 */

@NodeEntity
public class Ruta {
  @GraphId
  private Integer numero;
  
  @Indexed
  private String nombre;

  public Ruta(Integer numero, String nombre) {
    this.numero = numero;
    this.nombre = nombre;
  }

  public Integer getNumero() {
    return numero;
  }

  public String getNombre() {
    return nombre;
  }
  
  
}

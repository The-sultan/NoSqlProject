package edu.fing.tagsi.neo4j.domain;

import java.util.List;
import org.springframework.data.neo4j.annotation.*;

@RelationshipEntity(type = Ruta.RUTA)
public class Ruta {

  public static final String RUTA = "RUTA";
  @GraphId
  private Long id;
  @StartNode
  private Ciudad ciudadOrigen;
  @EndNode
  private Ciudad ciudadDestino;
  private double distancia;
  private List<Integer> rutas;

  public Ruta(Ciudad origen, Ciudad destino, List<Integer> rutas, double distancia) {
    this.ciudadOrigen = origen;
    this.ciudadDestino = destino;
    this.rutas = rutas;
    this.distancia = distancia;
  }

  public Ruta() {
  }

  public Long getId() {
    return id;
  }

  public Ciudad getCiudadDestino() {
    return ciudadDestino;
  }

  public Ciudad getCiudadOrigen() {
    return ciudadOrigen;
  }

  public List<Integer> getRutas() {
    return rutas;
  }

  public double getDistancia() {
    return distancia;
  }

  @Override
  public int hashCode() {
    return (id == null) ? 0 : id.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Ruta other = (Ruta) obj;
    if (id == null) {
      return other.id == null;
    }
    return id.equals(other.id);
  }

  @Override
  public String toString() {
    return String.format("Ruta{origen='%s', destino='%s', rutas='%s'}",
            ciudadOrigen.getName(), ciudadDestino.getName(), rutas.toString());
  }
}

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
    int hash = 5;
    hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
    hash = 89 * hash + (this.ciudadOrigen != null ? this.ciudadOrigen.hashCode() : 0);
    hash = 89 * hash + (this.ciudadDestino != null ? this.ciudadDestino.hashCode() : 0);
    hash = 89 * hash + (int) (Double.doubleToLongBits(this.distancia) ^ (Double.doubleToLongBits(this.distancia) >>> 32));
    hash = 89 * hash + (this.rutas != null ? this.rutas.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Ruta other = (Ruta) obj;
    if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
      return false;
    }
    if (this.ciudadOrigen != other.ciudadOrigen && (this.ciudadOrigen == null || !this.ciudadOrigen.equals(other.ciudadOrigen))) {
      return false;
    }
    if (this.ciudadDestino != other.ciudadDestino && (this.ciudadDestino == null || !this.ciudadDestino.equals(other.ciudadDestino))) {
      return false;
    }
    if (Double.doubleToLongBits(this.distancia) != Double.doubleToLongBits(other.distancia)) {
      return false;
    }
    if (this.rutas != other.rutas && (this.rutas == null || !this.rutas.equals(other.rutas))) {
      return false;
    }
    return true;
  }

  

 

  @Override
  public String toString() {
    return String.format("Ruta{origen='%s', destino='%s', rutas='%s'}",
            ciudadOrigen.getName(), ciudadDestino.getName(), rutas.toString());
  }
}

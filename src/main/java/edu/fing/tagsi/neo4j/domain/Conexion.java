package edu.fing.tagsi.neo4j.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@RelationshipEntity(type="CONECTADA_A")
public class Conexion {
	
    @GraphId
    private Long id;
    
    @EndNode
    private Ciudad ciudadDestino;
    
    @StartNode
    private Ciudad ciudadOrigen;

    private Ruta ruta;

    public Conexion(Ciudad origen, Ciudad destino, Ruta ruta) {
        this.ciudadOrigen = origen;
        this.ciudadDestino = destino;
        this.ruta = ruta;
    }

    public Conexion() {
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

  public Ruta getRuta() {
    return ruta;
  }
    
    
        

    

	@Override
	public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Conexion other = (Conexion) obj;
		if (id == null) return other.id == null;
        return id.equals(other.id);
    }
	
	@Override
    public String toString() {
        return String.format("Conexion{origen='%s', destino='%s', ruta='%s'}", ciudadOrigen.getName(), ciudadDestino.getName(), ruta.getNumero());
    }
}

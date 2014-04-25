package edu.fing.tagsi.neo4j.domain;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.*;

import java.util.Set;

@NodeEntity
public class Ciudad {
	private final static String CONECTADA_A = "CONECTADA_A";
	
    @GraphId
    private Long id;

    // Uses default schema based index
    @Indexed
    private String name;

    @Fetch
    @RelatedTo(type = CONECTADA_A, direction = Direction.BOTH)
    private Set<Ciudad> conectadaA;

    public Ciudad(String name) {
        this.name = name;
    }

    public Ciudad() {
    }

    public Long getId() {
    	return id;
    }
    
    public String getName() {
        return name;
    }

        

    public void agregarConexionA(Ciudad otraCiudad) {
    	conectadaA.add(otraCiudad);
    }
    
    public boolean seLlegaDesde(Ciudad otraCiudad) {
    	return conectadaA.contains(otraCiudad);
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
		Ciudad other = (Ciudad) obj;
		if (id == null) return other.id == null;
        return id.equals(other.id);
    }
	
	@Override
    public String toString() {
        return String.format("Ciudad{nombre='%s'}", name);
    }
}

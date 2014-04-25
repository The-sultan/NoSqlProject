package edu.fing.tagsi.neo4j.repositories;

import edu.fing.tagsi.neo4j.domain.Ciudad;
import org.springframework.data.neo4j.repository.GraphRepository;

public interface CiudadRepository extends GraphRepository<Ciudad> {}

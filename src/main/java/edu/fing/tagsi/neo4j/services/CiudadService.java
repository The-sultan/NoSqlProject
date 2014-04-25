package edu.fing.tagsi.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.repositories.CiudadRepository;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CiudadService {

  @Autowired
  private CiudadRepository ciudadRepository;

  public long getCantidadCiudades() {
    return ciudadRepository.count();
  }

  public Ciudad guardarCiudad(Ciudad ciudad) {
    return ciudadRepository.save(ciudad);
  }

  public Ciudad crearCiudad(String name) {
    return ciudadRepository.save(new Ciudad(name));
  }

  public Iterable<Ciudad> getTodasLasCiudades() {
    return ciudadRepository.findAll();
  }

  public Ciudad findCiudadById(Long id) {
    return ciudadRepository.findOne(id);
  }

  // This is using the schema based index
  public Ciudad findCiudadByName(String name) {
    return ciudadRepository.findBySchemaPropertyValue("name", name);
  }

  public Collection<Ciudad> crearAlgunasCiudades() {
    Collection<Ciudad> ciudades = new ArrayList<Ciudad>();
    ciudades.add(crearCiudad("Montevideo"));
    ciudades.add(crearCiudad("Canelones"));

    Ciudad rivera = crearCiudad("Rivera");
    Ciudad tacuarembo = crearCiudad("Tacuarembo");
    rivera.agregarConexionA(tacuarembo);
    ciudades.add(rivera);
    ciudades.add(tacuarembo);
    ciudadRepository.save(rivera);
    return ciudades;
  }
}

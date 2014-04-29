/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.fing.tagsi.neo4j.services;

import edu.fing.tagsi.neo4j.domain.Ruta;
import edu.fing.tagsi.neo4j.repositories.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Farid
 */
@Service
@Transactional
public class RutaService {
  
    
  @Autowired
  private RutaRepository rutaRepository;
  
  public Ruta guardarRuta(Ruta ruta){
    return rutaRepository.save(ruta);
  }
  
   public Ruta findRutaById(Long id){
    return rutaRepository.findOne(id);
  }
}

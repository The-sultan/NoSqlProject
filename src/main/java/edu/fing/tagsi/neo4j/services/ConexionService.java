package edu.fing.tagsi.neo4j.services;

import org.springframework.beans.factory.annotation.Autowired;
import edu.fing.tagsi.neo4j.domain.Ciudad;
import edu.fing.tagsi.neo4j.domain.Conexion;
import edu.fing.tagsi.neo4j.domain.Ruta;
import edu.fing.tagsi.neo4j.repositories.ConexionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class ConexionService {
	
	@Autowired
	private ConexionRepository conexionRepository;
	
	public long getCantidadCiudades() {
		return conexionRepository.count();
	}
	
	public Conexion crearConexion(Ciudad origen, Ciudad destino, Ruta ruta) {
		return conexionRepository.save(new Conexion(origen, destino, ruta));
	}
	
	public Iterable<Conexion> getTodasLasConexiones() {
		return conexionRepository.findAll();
	}
	
	public Conexion findConexionById(Long id) {
		return conexionRepository.findOne(id);
	}

	
	
	
}

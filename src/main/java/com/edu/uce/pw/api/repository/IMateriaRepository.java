package com.edu.uce.pw.api.repository;

import java.util.List;


import com.edu.uce.pw.api.repository.modelo.Materia;
import com.edu.uce.pw.api.service.TO.MateriaTO;

public interface IMateriaRepository {
    
	
	 Materia seleccionar(Integer id);
	
	 void actualizar(Materia materia);
	
	 void eliminar(Integer id);
	
	 void insertar(Materia materia);

	 List<Materia> seleccionarPorNombre(String nombre);

	 public List<Materia> seleccionarPorIdEstudiante(Integer idEstudiante);

  
}

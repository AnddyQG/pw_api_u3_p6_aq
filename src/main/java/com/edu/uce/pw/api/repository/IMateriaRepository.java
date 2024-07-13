package com.edu.uce.pw.api.repository;

import java.util.List;


import com.edu.uce.pw.api.repository.modelo.Materia;

public interface IMateriaRepository {
    
	
	 Materia seleccionar(Integer id);
	
	 void actualizar(Materia materia);
	
	 void eliminar(Integer id);
	
	 void insertar(Materia materia);

	 List<Materia> seleccionarPorNombre(String nombre);
     List<Materia> seleccionarPorIdEstudiante(Integer id);

}

package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.modelo.Materia;
import com.edu.uce.pw.api.service.TO.MateriaTO;

public interface IMateriaService {
    
    Materia buscar(Integer id);
	
	 void actualizar(Materia materia);
	
	 void borrar(Integer id);
	
	 void guardar(Materia materia);

	

	 List<MateriaTO> buscarPorIdEstudiante(Integer idEstu);

}

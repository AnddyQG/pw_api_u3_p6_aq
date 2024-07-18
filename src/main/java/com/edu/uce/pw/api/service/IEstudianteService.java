package com.edu.uce.pw.api.service;

import java.util.List;

import com.edu.uce.pw.api.repository.modelo.Estudiante;
import com.edu.uce.pw.api.service.TO.EstudianteTO;

public interface IEstudianteService {

	
	public Estudiante buscar(Integer id);
	
	public void actualizar(Estudiante estudiante);
	
	public void borrar(Integer id);
	
	public void guardar(Estudiante estudiante);

	List<Estudiante> buscarPorGenero(String genero);

public EstudianteTO buscarPorId(Integer id);

List<EstudianteTO>  selecEstudiantes();
}

package com.edu.uce.pw.api.repository;



import com.edu.uce.pw.api.repository.modelo.Estudiante;




public interface IEstudianteRepository {

	
	 Estudiante seleccionar(Integer id);
	
	 void actualizar(Estudiante estudiante);
	
	 void eliminar(Integer id);
	
	 void insertar(Estudiante estudiante);
	
	
}

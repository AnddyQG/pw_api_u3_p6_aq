package com.edu.uce.pw.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.uce.pw.api.repository.IEstudianteRepository;
import com.edu.uce.pw.api.repository.modelo.Estudiante;
import com.edu.uce.pw.api.service.TO.EstudianteTO;

@Service


public class EstudianteServiceImpl implements IEstudianteService{

	@Autowired
	private IEstudianteRepository estudianteRepository;
	
	@Override
	public Estudiante buscar(Integer id) {
		// TODO Auto-generated method stub
		return this.estudianteRepository.seleccionar(id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.actualizar(estudiante);
	}

	@Override
	public void borrar(Integer id) {
		// TODO Auto-generated method stub
		this.estudianteRepository.eliminar(id);
	}

	@Override
	public void guardar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.estudianteRepository.insertar(estudiante);
	}

	@Override
	public List<Estudiante> buscarPorGenero(String genero) {
	
		return this.estudianteRepository.seleccionarPorGenero(genero);
	}
  
    public EstudianteTO convertir(Estudiante e) {
        EstudianteTO estudianteTO = new EstudianteTO();
        estudianteTO.setId(e.getId());
        estudianteTO.setNombre(e.getNombre());
        estudianteTO.setApellido(e.getApellido());
   
        estudianteTO.setGenero(e.getGenero());
		estudianteTO.setCedula(e.getCedula());
	
        return estudianteTO;
    }

    @Override
    public EstudianteTO buscarPorId(Integer id) {
        Estudiante e = this.estudianteRepository.seleccionar(id);
        return this.convertir(e);
    }

	@Override
	public List<EstudianteTO> selecEstudiantes() {
	
   List<Estudiante> estudiantes = this.estudianteRepository.selecEstudiantes();
        List<EstudianteTO> estudiantesTO = new ArrayList<>();
        for (Estudiante e : estudiantes) {
            estudiantesTO.add(this.convertir(e));
        }
        return estudiantesTO; 

	}

	@Override
	public EstudianteTO buscarPorCedula(String cedula) {
	

		Estudiante e = this.estudianteRepository.buscarPorCedula(cedula);
		return this.convertir(e);
	}

	@Override
	public Estudiante actualizarCedula(String cedula) {
	
		return this.estudianteRepository.actualizarCedula(cedula);

		
	}

	@Override
	public void eliminarCedula(String cedula) {
	
this.estudianteRepository.eliminarCedula(cedula);


	}


}

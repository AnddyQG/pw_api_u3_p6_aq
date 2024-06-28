package com.edu.uce.pw.api.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.modelo.Estudiante;
import com.edu.uce.pw.api.service.IEstudianteService;
@RestController
@RequestMapping(path = "/estudiantes")
//Para pweb solo 5 tipos
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	
	
	//Post
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	@PostMapping(path = "/guardar")
	public void guardar() {
		Estudiante est= new Estudiante();
		est.setNombre("Anddy");
		est.setApellido("Quisilema");
		est.setFechaNacimiento(LocalDateTime.of(2000, 07, 19, 0, 0));
		this.estudianteService.guardar(est);
		
	}
	
	//patch act el estado parcial de un recurso
	//Put
	//Act completo de uno o varios recursos
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	@PatchMapping(path = "/actualizarParcial")
	public void actualizarParcial() {
		Estudiante est= this.estudianteService.buscar(1);
		est.setNombre("Rafael");
		this.estudianteService.actualizar(est);
	}
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	@PutMapping(path = "/actualizar")
	public void actualizar() {
		Estudiante est= this.estudianteService.buscar(1);
		est.setNombre("Rafael");
		est.setApellido("Guacollante");
		est.setFechaNacimiento(LocalDateTime.of(2000, 07, 07, 0, 0));
		this.estudianteService.actualizar(est);
	}
	//delete
//	http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
	@DeleteMapping(path = "/borrar")
	public void borrar() {
		this.estudianteService.borrar(1);
	}
	//Get
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar
	@GetMapping(path = "/buscar")
	public Estudiante buscar() {
		
		return this.estudianteService.buscar(2);
	}
	
}

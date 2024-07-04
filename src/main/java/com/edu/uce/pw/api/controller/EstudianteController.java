package com.edu.uce.pw.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.modelo.Estudiante;
import com.edu.uce.pw.api.service.IEstudianteService;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/estudiantes")
//Para pweb solo 5 tipos
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	
	
	//Post
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes
	@PostMapping
	public void guardar(@RequestBody Estudiante est) {

		this.estudianteService.guardar(est);
		
	}
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PutMapping(path = "/{id}")
	public void actualizar(@RequestBody Estudiante est,@PathVariable Integer id) {

		est.setId(id);
		this.estudianteService.actualizar(est);
	}
	
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PatchMapping(path = "/{id}")
	public void actualizarParcial(@RequestBody Estudiante est,@PathVariable Integer id) {
		est.setId(id);
		Estudiante est2 = this.estudianteService.buscar(est.getId());

		if (est.getNombre() != null) {
			est2.setNombre(est.getNombre());
		}
		if (est.getApellido() != null) {
			est2.setApellido(est.getApellido());
		}
		if (est.getFechaNacimiento() != null) {
			est2.setFechaNacimiento(est.getFechaNacimiento());
		}
		this.estudianteService.actualizar(est2);


	}
	
	//delete
//	http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@DeleteMapping(path = "/{id}")
	public void borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
	}
	//Get
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1/nuevo/prueba
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@GetMapping(path = "/{id}")
	public Estudiante buscar(@PathVariable Integer id ) {
		
		return this.estudianteService.buscar(id);
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=M&edad=35
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes
	@GetMapping(path = "/buscarPorGenero")
	 public List<Estudiante> buscarPorGenero(@RequestParam String genero,@RequestParam Integer edad) {
		System.out.println("Edad " + edad);
		List<Estudiante> ls= this.estudianteService.buscarPorGenero(genero);
		return ls;

	 }


	 	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarMixto/1?prueba=HolaMundo
	@GetMapping(path = "/buscarMixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		System.out.println("Dat : " + id);
		System.out.println("Dato : " + prueba );
		return this.estudianteService.buscar(id);
	}

}

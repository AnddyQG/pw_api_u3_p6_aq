package com.edu.uce.pw.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante est) {

		this.estudianteService.guardar(est);
		
		return  ResponseEntity.status(201).body(est);
	}
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PutMapping(path = "/{id}")
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante est,@PathVariable Integer id) {

		est.setId(id);
		this.estudianteService.actualizar(est);
		return ResponseEntity.status(238).body(est);
	}
	
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PatchMapping(path = "/{id}")
	public ResponseEntity<Estudiante> actualizarParcial(@RequestBody Estudiante est,@PathVariable Integer id) {
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
		
		return ResponseEntity.status(239).body(est2);

	}
	
	//delete
//	http://localhost:8080/API/v1.0/Matricula/estudiantes/borrar
// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<String> borrar(@PathVariable Integer id) {
		this.estudianteService.borrar(id);
		return ResponseEntity.status(240).body("Estudiante eliminado");
	}
	//Get
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1/nuevo/prueba
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@GetMapping(path = "/{id}")
	public ResponseEntity<Estudiante> buscar(@PathVariable Integer id ) {
		this.estudianteService.buscar(id);

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Estudiante Encontrado");
		return  new ResponseEntity<>(this.estudianteService.buscar(id),cabeceras,236);



		//return  ResponseEntity.status(236).body(this.estudianteService.buscar(id));
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=M&edad=35
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/genero
	@GetMapping(path = "/genero")
	 public List<Estudiante> buscarPorGenero(@RequestParam String genero) {
		
		List<Estudiante> ls= this.estudianteService.buscarPorGenero(genero);
		return ls;

	 }


	 	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarMixto/1?prueba=HolaMundo
		// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/mixto/1
	@GetMapping(path = "/mixto/{id}")
	public Estudiante buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		//System.out.println("Dat : " + id);
		//System.out.println("Dato : " + prueba );
		return this.estudianteService.buscar(id);
	}


		// http://localhost:8080/API/v1.0/Matricula/estudiantes/test/1
		@GetMapping(path = "/test/{id}")
		public Estudiante test(@PathVariable Integer id, @RequestBody Estudiante e) {
			System.out.println(e);
			return this.estudianteService.buscar(id);
		}

}
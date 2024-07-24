package com.edu.uce.pw.api.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.edu.uce.pw.api.service.IMateriaService;
import com.edu.uce.pw.api.service.TO.EstudianteTO;
import com.edu.uce.pw.api.service.TO.MateriaTO;
import org.springframework.web.bind.annotation.RequestParam;
//Esto se puede llevar al examen import del link
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/estudiantes")
//Para pweb solo 5 tipos
@CrossOrigin
public class EstudianteController {

	@Autowired
	private IEstudianteService estudianteService;
	@Autowired
	private IMateriaService iMateriaService;
	
	//Post
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/guardar
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes
	//produce como consume(recibes)
	@PostMapping(produces = "application/json",consumes = "application/json")
	public ResponseEntity<Estudiante> guardar(@RequestBody Estudiante est) {
		
		this.estudianteService.guardar(est);
		
		return  ResponseEntity.status(HttpStatus.CREATED).body(est);
	}
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PutMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Estudiante> actualizar(@RequestBody Estudiante est,@PathVariable Integer id) {

		est.setId(id);
		this.estudianteService.actualizar(est);
		return ResponseEntity.status(238).body(est);
	}
	
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizarParcial
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
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
/* 
	@DeleteMapping(path = "/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> borrar(@PathVariable Integer id) {

		
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_240", "Estudiante eliminado");
		cabeceras.add("valor", "Estudiante eliminado");
		this.estudianteService.borrar(id);
		return new ResponseEntity<>(null,cabeceras,240);

	}*/
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1 
    @DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> borrar(@PathVariable Integer id) {
		System.out.println("Borrar estudiante");
        this.estudianteService.borrar(id);
        return ResponseEntity.status(240).body("Estudiante eliminado");
    }

	//Get
	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscar/1/nuevo/prueba
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/1
	@GetMapping(path = "/{id}",produces = "application/xml")
	public ResponseEntity<Estudiante> buscar(@PathVariable Integer id ) {
		

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Estudiante Encontrado");
		return  new ResponseEntity<>(this.estudianteService.buscar(id),cabeceras,236);



		//return  ResponseEntity.status(236).body(this.estudianteService.buscar(id));
	}

	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarPorGenero?genero=M&edad=35
	// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/genero
	@GetMapping(path = "/genero",produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<List<Estudiante>> buscarPorGenero(@RequestParam String genero) {
		
		List<Estudiante> ls= this.estudianteService.buscarPorGenero(genero);
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_237", "Corresponde a la consulta de recursos por género");
		cabeceras.add("valor", "Estudiantes Encontrados");
		return new ResponseEntity<>(ls,cabeceras,237);

	 }


	 	// http://localhost:8080/API/v1.0/Matricula/estudiantes/buscarMixto/1?prueba=HolaMundo
		// Nivel 1 : http://localhost:8080/API/v1.0/Matricula/estudiantes/mixto/1
	@GetMapping(path = "/mixto/{id}" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> buscarMixto(@PathVariable Integer id, @RequestParam String prueba) {
		//System.out.println("Dat : " + id);
		//System.out.println("Dato : " + prueba );
	

		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_236", "Corresponde a la consulta de un recurso");
		cabeceras.add("valor", "Estudiante Encontrado");
		return  new ResponseEntity<>(this.estudianteService.buscar(id),cabeceras,236);


	}


		// http://localhost:8080/API/v1.0/Matricula/estudiantes/test/1
		@GetMapping(path = "/test/{id}")
		public Estudiante test(@PathVariable Integer id, @RequestBody Estudiante e) {
			System.out.println(e);
			return this.estudianteService.buscar(id);
		}

		// http://localhost:8080/API/v1.0/Matricula/estudiantes/texto/plano
		@GetMapping(path = "/texto/plano")
		public String prueba(){
			String prueba = "Texto de prueba";
			return prueba;
		}


	// http://localhost:8080/API/v1.0/Matricula/estudiantes/hateoas/1
	@GetMapping(path = "/hateoas/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public EstudianteTO buscarHateoas(@PathVariable("id") Integer id) {
		EstudianteTO estudianteTO = this.estudianteService.buscarPorId(id);
		//ERROR esto es una carga EAGER(Necesiste o no lo trae) 
		//List<MateriaTO> ls = this.iMateriaService.buscarPorIdEstudiante(id);
		//estudianteTO.setMaterias(ls); 
		
		Link miLink = linkTo(methodOn( EstudianteController.class).buscarMateriaPorIdEstudiante(id)).withRel("SusMaterias");
		estudianteTO.add(miLink);

		Link miLink2 = linkTo(methodOn(EstudianteController.class).buscar(id)).withSelfRel();

		estudianteTO.add(miLink2);

		return estudianteTO;
	}


//http://localhost:8080/API/v1.0/Matricula/estudiantes/7/materias get

@GetMapping(path = "/{id}/materias",produces = MediaType.APPLICATION_JSON_VALUE)
public List<MateriaTO> buscarMateriaPorIdEstudiante(@PathVariable Integer id) {
return this.iMateriaService.buscarPorIdEstudiante(id);

}


// http://localhost:8080/API/v1.0/Matricula/estudiantes/todos
@GetMapping(path = "/todos",produces = MediaType.APPLICATION_JSON_VALUE)
public List<EstudianteTO> selecEstudiantes() {
	List<EstudianteTO> ls = this.estudianteService.selecEstudiantes();

	ls.forEach(x->{
		Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(x.getId())).withRel("Materias");
		x.add(myLink);
	});
return ls;
}

	// Nivel 1 : http://localhost:8082/API/v1.0/Matricula/estudiantes/buscarPorCedula?cedula=123456
//Nivel 2 : http://localhost:8082/API/v1.0/Matricula/estudiantes/cedula{cedula}
	@GetMapping(path = "/{cedula}", produces = "application/json")
	public EstudianteTO buscarPorCedula(@PathVariable String cedula) {
		EstudianteTO estudiante = this.estudianteService.buscarPorCedula(cedula);
		Link myLink = linkTo(methodOn(EstudianteController.class).buscarMateriaPorIdEstudiante(estudiante.getId()))
				.withRel("susMaterias");
		return estudiante.add(myLink);
	}


		// http://localhost:8080/API/v1.0/Matricula/estudiantes/actualizar
	// Nivel 1 : http://localhost:8082/API/v1.0/Matricula/estudiantes/cedula/
	@PutMapping(path = "/cedula/{cedula}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Estudiante> actualizarCedula(@RequestBody Estudiante est,@PathVariable String cedula) {
		EstudianteTO est2= this.estudianteService.buscarPorCedula(cedula);
		est.setCedula(cedula);
		est.setId(est2.getId());
		 this.estudianteService.actualizarCedula(cedula);
		return ResponseEntity.status(238).body(est);
	}


		// Nivel 1 : http://localhost:8082/API/v1.0/Matricula/estudiantes/cedula
		@DeleteMapping(path = "cedula/{cedula}", produces = MediaType.TEXT_PLAIN_VALUE)
		public ResponseEntity<String> borrarCedula(@PathVariable String cedula) {
			System.out.println("Borrar estudiante");
			this.estudianteService.eliminarCedula(cedula); 
			return ResponseEntity.status(240).body("Estudiante eliminado");
		}

}
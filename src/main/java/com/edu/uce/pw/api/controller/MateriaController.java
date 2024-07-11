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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.uce.pw.api.repository.modelo.Materia;
import com.edu.uce.pw.api.service.IMateriaService;

@RestController
@RequestMapping(path="/materias")

public class MateriaController {

    @Autowired
    private IMateriaService materiaService;
	// http://localhost:8080/API/v1.0/Matricula/materias/guardar
    	// Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias
    @PostMapping
    public ResponseEntity<Materia> guardar(@RequestBody Materia mat){
        this.materiaService.guardar(mat);
        return ResponseEntity.status(201).body(mat);

    }

    	// http://localhost:8080/API/v1.0/Matricula/materias/actualizar
        // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
        @PutMapping(path = "/{id}")
        public ResponseEntity<Materia> actualizar(@RequestBody Materia mat, @PathVariable Integer id){
            mat.setId(id);
            this.materiaService.actualizar(mat);
            return ResponseEntity.status(238).body(mat);
        }

	// http://localhost:8080/API/v1.0/Matricula/materias/actualizarParcial
     // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
@PatchMapping(path = "/{id}")
    public ResponseEntity<Materia> actualizarParcial(@RequestBody Materia mat,@PathVariable Integer id){
mat.setId(id);
        Materia mat2 =this.materiaService.buscar(mat.getId());
        if(mat.getNombre()!=null){
            mat2.setNombre(mat.getNombre());
        }
        if(mat.getCreditos()!=null){
            mat2.setCreditos(mat.getCreditos());
        }
        this.materiaService.actualizar(mat2);
    
return ResponseEntity.status(239).body(mat2);
    }

	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/2
     // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> borar(@PathVariable Integer id){
        this.materiaService.borrar(id);
HttpHeaders cabeceras = new HttpHeaders();
cabeceras.add("Mensaje_240", "Materia eliminado");
cabeceras.add("valor", "Materia eliminado");
this.materiaService.borrar(id);
return new ResponseEntity<>(null,cabeceras,240);

    }

    	// http://localhost:8080/API/v1.0/Matricula/materias/buscar/3/nuevo
         // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
    @GetMapping(path = "/{id}")
    public ResponseEntity<Materia> buscar(@PathVariable Integer id){
        HttpHeaders cabeceras = new HttpHeaders();
        cabeceras.add("Mensaje_236", "Materia encontrado");
        cabeceras.add("valor", "Materia encontrado");
        
        return new ResponseEntity<>(this.materiaService.buscar(id),cabeceras,236);
    }


    	// http://localhost:8080/API/v1.0/Matricula/materias/buscarPorGenero
         // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/nombre?nombre=Analisis 1 
    @GetMapping(path = "/nombre")
    public ResponseEntity<List<Materia>> buscarPorNombre(@RequestParam String nombre){
        
List<Materia> ms = this.materiaService.buscarPorNombre(nombre);
HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add("Mensaje_237", "Corresponde a la consulta por nombre");
		cabeceras.add("valor", "Materia Encontrados");
		return new ResponseEntity<>(ms,cabeceras,237);
      
    }


 

}

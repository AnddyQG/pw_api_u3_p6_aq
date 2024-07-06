package com.edu.uce.pw.api.controller;

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
    public void guardar(@RequestBody Materia mat){
        this.materiaService.guardar(mat);

    }

    	// http://localhost:8080/API/v1.0/Matricula/materias/actualizar
        // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
        @PutMapping(path = "/{id}")
        public void actualizar(@RequestBody Materia mat, @PathVariable Integer id){
            mat.setId(id);
            this.materiaService.actualizar(mat);
        }

	// http://localhost:8080/API/v1.0/Matricula/materias/actualizarParcial
     // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
@PatchMapping(path = "/{id}")
    public void actualizarParcial(@RequestBody Materia mat,@PathVariable Integer id){
mat.setId(id);
        Materia mat2 =this.materiaService.buscar(mat.getId());
        if(mat.getNombre()!=null){
            mat2.setNombre(mat.getNombre());
        }
        if(mat.getCreditos()!=null){
            mat2.setCreditos(mat.getCreditos());
        }
        this.materiaService.actualizar(mat2);
    

    }

	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/2
     // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
    @DeleteMapping(path = "/{id}")
    public void borar(@PathVariable Integer id){
        this.materiaService.borrar(id);
    }

    	// http://localhost:8080/API/v1.0/Matricula/materias/buscar/3/nuevo
         // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/1
    @GetMapping(path = "/{id}")
    public Materia buscar(@PathVariable Integer id){
        return this.materiaService.buscar(id);
    }


    	// http://localhost:8080/API/v1.0/Matricula/materias/buscarPorGenero
         // Nivel 1 http://localhost:8080/API/v1.0/Matricula/materias/nombre?nombre=Analisis 1 
    @GetMapping(path = "/nombre")
    public List<Materia> buscarPorNombre(@RequestParam String nombre){
        
List<Materia> ms = this.materiaService.buscarPorNombre(nombre);
        return ms;

    }


 

}

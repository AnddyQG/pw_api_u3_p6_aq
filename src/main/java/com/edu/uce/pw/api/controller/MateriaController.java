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
    @PostMapping
    public void guardar(@RequestBody Materia mat){
        this.materiaService.guardar(mat);

    }
	// http://localhost:8080/API/v1.0/Matricula/materias/actualizarParcial
@PatchMapping(path = "/actualizarParcial")
    public void actualizarParcial(@RequestBody Materia mat){

        Materia mat2 =this.materiaService.buscar(mat.getId());
        if(mat.getNombre()!=null){
            mat2.setNombre(mat.getNombre());
        }
        if(mat.getCreditos()!=null){
            mat2.setCreditos(mat.getCreditos());
        }
        this.materiaService.actualizar(mat2);
    

    }
	// http://localhost:8080/API/v1.0/Matricula/materias/actualizar
    @PutMapping(path = "/actualizar")
    public void actualizar(@RequestBody Materia mat){
        this.materiaService.actualizar(mat);
    }
	// http://localhost:8080/API/v1.0/Matricula/materias/borrar/2
    @DeleteMapping(path = "/borrar/{id}")
    public void borar(@PathVariable Integer id){
        this.materiaService.borrar(id);
    }

    	// http://localhost:8080/API/v1.0/Matricula/materias/buscar/3/nuevo
    @GetMapping(path = "/buscar/{id}/nuevo")
    public Materia buscar(@PathVariable Integer id){
        return this.materiaService.buscar(1);
    }


    	// http://localhost:8080/API/v1.0/Matricula/materias/buscarPorGenero
    @GetMapping(path = "/buscarPorNombre")
    public List<Materia> buscarPorNombre(@RequestParam String nombre){
        
List<Materia> ms = this.materiaService.buscarPorNombre(nombre);
        return ms;

    }

}

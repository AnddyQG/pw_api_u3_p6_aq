package com.edu.uce.pw.api.service.TO;

import java.util.List;
import java.io.Serializable;


import org.springframework.hateoas.RepresentationModel;
 
public class EstudianteTO extends RepresentationModel<EstudianteTO> implements Serializable{
 
    private static final long serialVersionUID = 7085562941894409720L;
 
    private Integer id;
    private String nombre;
    private String apellido;

    private String genero;
    	private String cedula;

    private List<MateriaTO> materia;
   // private List<MateriaTO> materias;
 
 
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
   
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public String getCedula() {
        return cedula;
    }
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public List<MateriaTO> getMateria() {
        return materia;
    }
    public void setMateria(List<MateriaTO> materia) {
        this.materia = materia;
    }
   


 
   
   
}
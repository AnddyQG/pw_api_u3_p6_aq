package com.edu.uce.pw.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import com.edu.uce.pw.api.repository.modelo.Materia;
@Repository
@Transactional
public class MateriaRepositoryImpl implements IMateriaRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Materia seleccionar(Integer id) {
     
        return this.entityManager.find(Materia.class, id);
    }

    @Override
    public void actualizar(Materia materia) {

        this.entityManager.merge(materia);

    }

    @Override
    public void eliminar(Integer id) {
    
        this.entityManager.remove(this.seleccionar(id));

    }

    @Override
    public void insertar(Materia materia) {
  
        this.entityManager.persist(materia);
    }

    @Override
    public List<Materia> seleccionarPorNombre(String nombre) {
   
        String jpql = "SELECT e FROM Materia e WHERE e.nombre=:nombre";
        TypedQuery<Materia> query = this.entityManager.createQuery(jpql,Materia.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
   
    }

    @Override
    public List<Materia> seleccionarPorIdEstudiante(Integer idEstudiante) {
        String jpql = "SELECT m FROM Materia m WHERE m.estudiante.id = :idEstudiante";
        TypedQuery<Materia> myQuery = this.entityManager.createQuery(jpql, Materia.class);
        myQuery.setParameter("idEstudiante", idEstudiante);
        return myQuery.getResultList();
    }

    
}

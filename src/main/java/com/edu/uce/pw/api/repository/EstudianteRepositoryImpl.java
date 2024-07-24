package com.edu.uce.pw.api.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.edu.uce.pw.api.repository.modelo.Estudiante;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
@Transactional

public class EstudianteRepositoryImpl implements IEstudianteRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Estudiante seleccionar(Integer id) {
		// TODO Auto-generated method stub
		return this.entityManager.find(Estudiante.class, id);
	}

	@Override
	public void actualizar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.merge(estudiante);
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
		this.entityManager.remove(this.seleccionar(id));
	}

	@Override
	public void insertar(Estudiante estudiante) {
		// TODO Auto-generated method stub
		this.entityManager.persist(estudiante);
		
	}

	@Override
	public List<Estudiante> seleccionarPorGenero(String genero) {

String jpql = "SELECT e FROM Estudiante e WHERE e.genero=:genero";
TypedQuery<Estudiante> myQuery = this.entityManager.createQuery(jpql,Estudiante.class);
myQuery.setParameter("genero", genero);
return myQuery.getResultList();
	}

	@Override
	public List<Estudiante> selecEstudiantes() {

String jpql = "SELECT e FROM Estudiante e";

TypedQuery<Estudiante> myQuery = this.entityManager.createQuery(jpql,Estudiante.class);

return myQuery.getResultList();

	}

	
	@Override
    public Estudiante buscarPorCedula(String cedula) {
      
        TypedQuery<Estudiante> query = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.cedula=: cedula ", Estudiante.class);
		query.setParameter("cedula", cedula);
		return query.getSingleResult();  
   
    }

	@Override
	public Estudiante actualizarCedula(String cedula) {

		TypedQuery<Estudiante> query = this.entityManager.createQuery("SELECT e FROM Estudiante e WHERE e.cedula=: cedula ", Estudiante.class);
		query.setParameter("cedula", cedula);

	return query.getSingleResult();
	}

	@Override
	public void eliminarCedula(String cedula) {
		String jpql = "DELETE FROM Estudiante e WHERE e.cedula = :cedula";
		Query query = this.entityManager.createQuery(jpql);
		query.setParameter("cedula", cedula);
		query.executeUpdate();
		

	}

}

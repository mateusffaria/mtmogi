package br.com.mtmogi.mtmogi.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.mtmogi.mtmogi.model.Salario;

@Repository
@Transactional
public class SalarioDAO {

	@PersistenceContext
	private EntityManager em;

	public List<Salario> getSalaryAll() {
		TypedQuery<Salario> query = em.createQuery("from Salario", Salario.class);

		return query.getResultList();
	}
	
	public void save(Salario salario) {
			em.persist(salario);
	}
	
	/**
	 * Method for obtaining the current salary of a public servant
	 * @param id Single identify of server
	 * @return Salario Containing the current salary
	 */
	public Salario getCurrentSalaryByServer(Long id) {
		
		return em.createQuery
				 ("SELECT distinct(s) "
				+ "FROM Salario s "
				+ "WHERE s.servidor.id = :id "
				+ "ORDER BY s.dataAtualizacao desc ", Salario.class).setMaxResults(1)
				 	.setParameter("id",id)
				 	.getSingleResult();
		
	}
	
	/**
	 * Method for obtaining all salary´s of a public servant
	 * @param id Single identify of server
	 * @return List<Salario> Containing all salary´s of a servant
	 */
	public List<Salario> getAllSalaryOfAServer(Long id) {
			
			return em.createQuery
					 ("SELECT s "
					+ "FROM Salario s "
					+ "WHERE s.servidor.id = :id "
					+ "ORDER BY s.dataAtualizacao desc ", Salario.class)
					 	.setParameter("id",id)
					 	.getResultList();
			
	}
	
	
	
	
}

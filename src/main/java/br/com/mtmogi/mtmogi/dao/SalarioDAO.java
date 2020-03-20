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
	
}

package br.com.mtmogi.mtmogi.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import br.com.mtmogi.mtmogi.model.SalarioDesconto;

@Repository
@Transactional
public class SalarioDAO {

	@PersistenceContext
	private EntityManager em;

	public List<SalarioDesconto> getSalaryAll() {
		TypedQuery<SalarioDesconto> query = em.createQuery("from SalarioDesconto", SalarioDesconto.class);

		return query.getResultList();
	}

	public void save(SalarioDesconto salario) {
		em.persist(salario);
	}

	/**
	 * Method for obtaining the current salary of a public servant
	 * 
	 * @param id Single identify of server
	 * @return Salario Containing the current salary
	 */
	public SalarioDesconto getCurrentSalaryByServer(Long id) {

		return em
				.createQuery("SELECT distinct(s) " + "FROM SalarioDesconto s " + "WHERE s.servidor.id = :id "
						+ "ORDER BY s.dataAtualizacao desc ", SalarioDesconto.class)
				.setMaxResults(1).setParameter("id", id).getSingleResult();

	}

	/**
	 * Method for obtaining all salary´s of a public servant
	 * 
	 * @param id Single identify of server
	 * @return List<Salario> Containing all salary´s of a servant
	 */
	public List<SalarioDesconto> getAllSalaryOfAServer(Long id) {

		return em
				.createQuery("SELECT s " + "FROM SalarioDesconto s " + "WHERE s.servidor.id = :id "
						+ "ORDER BY s.dataAtualizacao desc ", SalarioDesconto.class)
				.setParameter("id", id).getResultList();

	}

	public List<BigDecimal> getAllGrossIncoming(Long id) {
		return em
				.createQuery("SELECT SUM(s.valor) as valor" + " FROM SalarioDesconto s " + "WHERE s.servidor.id = :id "
						+ "AND s.tipo like '%RENDIMENTO%' AND s.referencia = "
						+ "(SELECT referencia_atual FROM Configuracao c WHERE c.id = c.id )", BigDecimal.class)
				.setParameter("id", id).getResultList();
	}

	public List<BigDecimal> getAllDiscountSalary(Long id) {
		return em
				.createQuery("SELECT SUM(s.valor) as valor" + " FROM SalarioDesconto s " + "WHERE s.servidor.id = :id "
						+ "AND s.tipo like '%DESCONTO%' AND s.referencia = "
						+ "(SELECT referencia_atual FROM Configuracao c WHERE c.id = c.id )", BigDecimal.class)
				.setParameter("id", id).getResultList();
	}

}

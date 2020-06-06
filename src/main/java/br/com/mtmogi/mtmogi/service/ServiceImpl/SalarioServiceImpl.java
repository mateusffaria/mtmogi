package br.com.mtmogi.mtmogi.service.ServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.service.SalarioService;

@Service
public class SalarioServiceImpl implements SalarioService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Salario> getSalaryAll() {
		TypedQuery<Salario> query = em.createQuery("from Salario", Salario.class);

		return query.getResultList();
	}

	@Override
	public Salario getCurrentSalaryByServer(Long id) {

		return em
				.createQuery("SELECT distinct(s) " + "FROM Salario s " + "WHERE s.servidor.id = :id "
						+ "ORDER BY s.dataAtualizacao desc ", Salario.class)
				.setMaxResults(1).setParameter("id", id).getSingleResult();

	}

	@Override
	public List<Salario> getAllSalaryOfAServer(Long id) {
		
		return em
				.createQuery("SELECT s " + "FROM Salario s " + "WHERE s.servidor.id = :id "
						+ "ORDER BY s.dataAtualizacao desc ", Salario.class)
				.setParameter("id", id).getResultList();

	}

	@Override
	public List<BigDecimal> getAllGrossIncoming(Long id) {

		return em
				.createQuery("SELECT SUM(s.valor) as valor" + " FROM Salario s " + "WHERE s.servidor.id = :id "
						+ "AND s.tipo like '%RENDIMENTO%' AND s.referencia = "
						+ "(SELECT referencia_atual FROM Configuracao c WHERE c.id = c.id )", BigDecimal.class)
				.setParameter("id", id).getResultList();		
	}

	@Override
	public List<BigDecimal> getAllDiscountSalary(Long id) {
		
		return em
				.createQuery("SELECT SUM(s.valor) as valor" + " FROM Salario s " + "WHERE s.servidor.id = :id "
						+ "AND s.tipo like '%DESCONTO%' AND s.referencia = "
						+ "(SELECT referencia_atual FROM Configuracao c WHERE c.id = c.id )", BigDecimal.class)
				.setParameter("id", id).getResultList();		
	}

}

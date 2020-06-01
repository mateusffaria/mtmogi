package br.com.mtmogi.mtmogi.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ConfiguracaoDAO {

	@PersistenceContext
	private EntityManager em;

	public void updateReferenciaAtual(String configNova, Long id) {

		int result = em.createQuery("UPDATE FROM Configuracao AS c SET c.referencia_atual = :configNova WHERE id = :id")
				.setParameter("id", id).setParameter("configNova", configNova).executeUpdate();
	}
}

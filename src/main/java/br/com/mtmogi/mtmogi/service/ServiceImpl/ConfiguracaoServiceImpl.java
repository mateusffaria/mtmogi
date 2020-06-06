package br.com.mtmogi.mtmogi.service.ServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mtmogi.mtmogi.service.ConfiguracaoService;

@Service
@Transactional
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void updateReferenciaAtual(String configNova, Long id) {
		int result = em.createQuery("UPDATE FROM Configuracao AS c SET c.referencia_atual = :configNova WHERE id = :id")
				.setParameter("id", id).setParameter("configNova", configNova).executeUpdate();
	}

}

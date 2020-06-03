package br.com.mtmogi.mtmogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.mtmogi.mtmogi.model.Servidor;

public interface MtmogiRepository extends CrudRepository<Servidor, Long>{
    public List<Servidor> findByNomeLike(String nome);
    public List<Servidor> findByCargoLike(String cargo);
    
    @Query(value="SELECT servidor.id, cargo, nome, rgf, (SELECT SUM(sal.valor) FROM salario_desconto as sal WHERE servidor.id = sal.id AND sal.tipo = 'RENDIMENTOS') AS rendimentos FROM servidor as servidor OFFSET :start LIMIT :length", nativeQuery = true)
	List<Object> findServersWithPage(@Param("start") int start, 
									 @Param("length") int length);
	
	@Query(value="SELECT COUNT(*) " + 
			"FROM(SELECT servidor.id, cargo, nome, rgf, "
			+ "(SELECT SUM(sal.valor) FROM salario_desconto as sal WHERE servidor.id = sal.id AND sal.tipo = 'RENDIMENTOS') AS salario " + 
			"FROM servidor ) as servidores", nativeQuery = true)
	int totalServers();
	
}
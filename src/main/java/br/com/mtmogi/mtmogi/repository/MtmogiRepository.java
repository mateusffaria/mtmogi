package br.com.mtmogi.mtmogi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.mtmogi.mtmogi.model.Servidor;

public interface MtmogiRepository extends CrudRepository<Servidor, Long>{
    public List<Servidor> findByNomeLike(String nome);
    public List<Servidor> findByCargoLike(String cargo);
	
}
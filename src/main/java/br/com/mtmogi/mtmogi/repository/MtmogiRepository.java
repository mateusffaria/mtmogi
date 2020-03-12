package br.com.mtmogi.mtmogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtmogi.mtmogi.model.Servidor;

public interface MtmogiRepository extends JpaRepository<Servidor, Long>{
    public List<Servidor> findByNomeLike(String nome);
    public List<Servidor> findByFuncaoLike(String funcao);

}
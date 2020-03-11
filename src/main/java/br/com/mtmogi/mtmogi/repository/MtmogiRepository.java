package br.com.mtmogi.mtmogi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;

public interface MtmogiRepository extends JpaRepository<Servidor, Long>{
    public List<Servidor> findByNome(String nome);

	public Salario save(Salario salario);
}
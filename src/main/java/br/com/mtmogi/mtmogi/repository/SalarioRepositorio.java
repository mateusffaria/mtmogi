package br.com.mtmogi.mtmogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtmogi.mtmogi.model.Salario;

public interface SalarioRepositorio extends JpaRepository<Salario, Long> {

}

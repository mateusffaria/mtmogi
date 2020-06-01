package br.com.mtmogi.mtmogi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mtmogi.mtmogi.model.SalarioDesconto;

public interface SalarioRepositorio extends JpaRepository<SalarioDesconto, Long> {

}

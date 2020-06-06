package br.com.mtmogi.mtmogi.service;

import java.math.BigDecimal;
import java.util.List;

import br.com.mtmogi.mtmogi.model.Salario;

public interface SalarioService {
	public List<Salario> getSalaryAll();
	public Salario getCurrentSalaryByServer(Long id);
	public List<Salario> getAllSalaryOfAServer(Long id);
	public List<BigDecimal> getAllGrossIncoming(Long id);
	public List<BigDecimal> getAllDiscountSalary(Long id);
}

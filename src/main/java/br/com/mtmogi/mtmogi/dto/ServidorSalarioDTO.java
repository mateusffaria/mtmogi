package br.com.mtmogi.mtmogi.dto;

import java.math.BigDecimal;

public class ServidorSalarioDTO {

	private int id;
	private String cargo;
	private String nome;
	private String rgf;
	private BigDecimal salario;
	
	public ServidorSalarioDTO() {
		
	}
	
	public ServidorSalarioDTO(int id, String cargo, String nome, String rgf, BigDecimal salario) {
		
		this.id = id;
		this.cargo = cargo;
		this.nome = nome;
		this.rgf = rgf;
		this.salario = salario;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRgf() {
		return rgf;
	}
	public void setRgf(String rgf) {
		this.rgf = rgf;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	
	
}

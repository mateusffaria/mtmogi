package br.com.mtmogi.mtmogi.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class Desconto {
	
	@NotNull
	private String descricao;
	
	@NotNull
	private BigDecimal valor;
	
	//Getters and Setters
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}

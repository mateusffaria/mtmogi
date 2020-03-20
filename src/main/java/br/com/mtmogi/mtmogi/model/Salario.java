package br.com.mtmogi.mtmogi.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="SALARIO")
public class Salario{
   
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="salario_sequence")
	@SequenceGenerator(name = "salario_sequence", sequenceName = "sal_seq", initialValue = 10000, allocationSize = 1)
    private Long id_sal;

    private BigDecimal valor;
    
    @DateTimeFormat
    private Calendar dataAtualizacao;

	@ManyToOne
    @JoinColumn(name="id")
    private Servidor servidor;
    
    @ElementCollection
    private List<Desconto> descontos;
    
    public List<Desconto> getDescontos() {
		return descontos;
	}

	public void setDescontos(List<Desconto> descontos) {
		this.descontos = descontos;
	}

	//Getters and Setters
	public Long getId_sal() {
		return id_sal;
	}

	public void setId_sal(Long id_sal) {
		this.id_sal = id_sal;
	} 

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}
    
   
}
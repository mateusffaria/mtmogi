package br.com.mtmogi.mtmogi.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="salario_desconto")
public class SalarioDesconto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id_sal;
	private String descricao, tipo, regime, referencia;
	private BigDecimal valor;
	
    @DateTimeFormat
    private Calendar dataAtualizacao;
	
	@ManyToOne
    @JoinColumn(name="id")
    private Servidor servidor;
}

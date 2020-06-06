package br.com.mtmogi.mtmogi.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="salario_desconto", uniqueConstraints = {
								@UniqueConstraint(
										columnNames = {
												"descricao","tipo","regime","referencia","valor","id"
										})
								})
public class Salario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id_sal;
	
	private String descricao, tipo, regime, referencia;
	
	private BigDecimal valor;
	
    @DateTimeFormat
    private Calendar dataAtualizacao;
	
	@ManyToOne
    @JoinColumn(name="id")
    private Servidor servidor;
}

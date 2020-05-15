package br.com.mtmogi.mtmogi.model;


import java.util.Collections;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import br.com.mtmogi.mtmogi.utils.CalendarComparator;

@Entity
@Table(name="SERVIDOR")
public class Servidor{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="servidor_sequence")
	@SequenceGenerator(name = "servidor_sequence", sequenceName = "serv_seq", initialValue = 200000, allocationSize = 1)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String funcao;
    
    @ElementCollection
    @OneToMany(mappedBy="servidor", targetEntity = Salario.class)
    private List<Salario> salarios;
    
    @Transient
    public Salario getSalarioAtual() {
    	
    	if(salarios != null) {
    		return getSalarios().get(0);
    	}
    	
		return null;
    }
    
    public List<Salario> getSalarios() {
    	
    	Collections.sort(salarios, new CalendarComparator());
 
		return salarios;
	}

	public void setSalarios(List<Salario> salarios) {
		this.salarios = salarios;
	}

	public String teste(){
		
		return"funfa";
	}
	
	
	//Getters and Setters
	public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }


}
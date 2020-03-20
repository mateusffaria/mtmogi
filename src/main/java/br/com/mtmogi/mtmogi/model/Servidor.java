package br.com.mtmogi.mtmogi.model;


import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="SERVIDOR")
public class Servidor{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="servidor_sequence")
	@SequenceGenerator(name = "servidor_sequence", sequenceName = "serv_seq")
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String funcao;
    
    @ElementCollection
    @OneToMany(mappedBy="servidor", targetEntity = Salario.class)
    private List<Salario> salarios;
    
    public List<Salario> getSalarios() {
		return salarios;
	}

	public void setSalarios(List<Salario> salarios) {
		this.salarios = salarios;
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
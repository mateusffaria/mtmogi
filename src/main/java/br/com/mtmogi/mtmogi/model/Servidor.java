package br.com.mtmogi.mtmogi.model;


import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="SERVIDOR")
public class Servidor{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String cargo;
    
    @NotEmpty
    private String rgf;
    
    @ElementCollection
    @OneToMany(mappedBy="servidor", targetEntity = SalarioDesconto.class)
    private List<SalarioDesconto> salarios;
    
    @Transient
    public SalarioDesconto getSalarioAtual() {
    	
    	if(salarios != null) {
    		return getSalarios().get(0);
    	}
    	
		return null;
    }
    
	public String teste(){
		
		return"funfa";
	}


}
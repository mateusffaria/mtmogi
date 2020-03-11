package br.com.mtmogi.mtmogi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="salario")
public class Salario{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private double valor;

    @NotBlank
    @NotNull
    private Long salservid;

    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public double getvalor() {
        return valor;
    }

    public void setvalor(double valor) {
        this.valor = valor;
    }

    public Long getsalservid() {
        return salservid;
    }

    public void setsalservid(Long salservid) {
        this.salservid = salservid;
    }

}
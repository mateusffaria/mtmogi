package br.com.mtmogi.mtmogi.utils;

import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.Desconto;
import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.repository.MtmogiRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import javax.annotation.PostConstruct;

@Component
public class DummyData {

    @Autowired
    MtmogiRepository mtmogi;
    
    @Autowired
    SalarioDAO salDAO;

    //@PostConstruct
    public void savePosts(){
    	
    	//Instanciando listas
        List<Servidor> servidores = new ArrayList<>();
        List<Salario> salarios = new ArrayList<>();
        List<Desconto> descontos = new ArrayList<>();
        
        //Instanciando Servidores
        Servidor servidor1 = new Servidor();
        servidor1.setNome("Marcus Melo");
        servidor1.setFuncao("Prefeito");

        Servidor servidor2 = new Servidor();
        servidor2.setNome("Juliano Abe");
        servidor2.setFuncao("Vice-Prefeito");
        
        //Instanciando Salarios
        Salario salario1 = new Salario();
        Salario salario2 = new Salario();
        
        salario1.setValor(new BigDecimal(20000.25));
        salario1.setDataAtualizacao(Calendar.getInstance());
        salario1.setDescontos(descontos);
           
        salario2.setValor(new BigDecimal(17000.80));
        salario2.setDataAtualizacao(Calendar.getInstance());
        salario2.setDescontos(descontos);
        
        //Instanciando Descontos
        Desconto desconto1 = new Desconto();
        Desconto desconto2 = new Desconto();
        
        desconto1.setDescricao("INSS");
        desconto1.setValor(new BigDecimal(1500.35));
        
        desconto2.setDescricao("FGTS");
        desconto2.setValor(new BigDecimal(1350.59));

        //Adicionando servidores à lista
        servidores.add(servidor1);
        servidores.add(servidor2);
 
        //Adicionando salários à lista
        salarios.add(salario1);
        salarios.add(salario2);
        
        
        //Adicionando descontos à lista
        descontos.add(desconto1);
        descontos.add(desconto2);
        
        System.out.println("Iniciando procedimentos de persistência");
        for(Salario salario: salarios){
        	for(Servidor servidor: servidores){
        		//Persistindo o servidor
        		Servidor servidorSaved = new Servidor();
        		servidorSaved = mtmogi.save(servidor);     	
	        	System.out.println("\nServidor -> ID: " + servidorSaved.getId() + " |Nome: " + servidorSaved.getNome());
	        	
	        	//Persistindo o salário
	        	Salario sal = new Salario();
	        	sal.setValor(salario.getValor());
	        	sal.setDescontos(descontos);
	        	sal.setDataAtualizacao(salario.getDataAtualizacao());
	        	sal.setServidor(servidorSaved);
	        	salDAO.save(sal);
	            System.out.println("Salário : " + sal.getValor().doubleValue());
        	}
        }
        	
   }
}
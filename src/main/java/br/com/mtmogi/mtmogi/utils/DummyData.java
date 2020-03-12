package br.com.mtmogi.mtmogi.utils;

import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.repository.MtmogiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    MtmogiRepository mtmogi;

    //@PostConstruct
    public void savePosts(){

         List<Servidor> servidorList = new ArrayList<>();
        Servidor servidor1 = new Servidor();
        servidor1.setNome("Marcus Melo");
        servidor1.setFuncao("Prefeito");

        Servidor servidor2 = new Servidor();
        servidor2.setNome("Juliano Abe");
        servidor2.setFuncao("Vice-Prefeito");

        servidorList.add(servidor1);
        servidorList.add(servidor2);

        for(Servidor servidor: servidorList){
            Servidor servidorSaved = mtmogi.save(servidor);
            System.out.println(servidorSaved.getId());
        } 

        List<Salario> salarioList = new ArrayList<>();
        Salario salarioPrefeito = new Salario();
        Salario salarioVicePrefeito = new Salario();

        salarioPrefeito.setvalor(25000.00d);
        salarioPrefeito.setsalservid(mtmogi.findByNomeLike("Marcus Melo").get(1).getId());

        salarioVicePrefeito.setvalor(20000.00d);
        salarioPrefeito.setsalservid(mtmogi.findByNomeLike("Juliano Abe").get(1).getId());

        salarioList.add(salarioPrefeito);
        salarioList.add(salarioVicePrefeito);

        //for(Salario salario: salarioList){
            //Salario salarioSaved = mtmogi.save(salario);
            //System.out.println(salarioSaved.getid());
        //}        

    }
}
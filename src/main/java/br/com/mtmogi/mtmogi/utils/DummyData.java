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
        servidor1.setnome("Marcus Melo");
        servidor1.setfuncao("Prefeito");

        Servidor servidor2 = new Servidor();
        servidor2.setnome("Juliano Abe");
        servidor2.setfuncao("Vice-Prefeito");

        servidorList.add(servidor1);
        servidorList.add(servidor2);

        for(Servidor servidor: servidorList){
            Servidor servidorSaved = mtmogi.save(servidor);
            System.out.println(servidorSaved.getid());
        } 

        List<Salario> salarioList = new ArrayList<>();
        Salario salarioPrefeito = new Salario();
        Salario salarioVicePrefeito = new Salario();

        salarioPrefeito.setvalor(25000.00d);
        salarioPrefeito.setsalservid(mtmogi.findByNome("Marcus Melo").get(1).getid());

        salarioVicePrefeito.setvalor(20000.00d);
        salarioPrefeito.setsalservid(mtmogi.findByNome("Juliano Abe").get(1).getid());

        salarioList.add(salarioPrefeito);
        salarioList.add(salarioVicePrefeito);

        for(Salario salario: salarioList){
            Salario salarioSaved = mtmogi.save(salario);
            System.out.println(salarioSaved.getid());
        }        

    }
}
package br.com.mtmogi.mtmogi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.MtmogiService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MtmogiController {

    @Autowired
    MtmogiService mtMogi;
    
    @Autowired
    SalarioDAO DAOSalario; 

    @RequestMapping(value="/prefeito", method=RequestMethod.GET)
    public ModelAndView getServidores() {
        ModelAndView mView = new ModelAndView("prefeito");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Prefeito");
        
        for (Servidor servidor : servidores) {
        	//Obtem a lista de salarios ordenados pela data, sendo o primeiro da lista o mais atual.
        	List<Salario>salariosOrdenados;
        	salariosOrdenados = DAOSalario.getAllSalaryOfAServer(servidor.getId());
        	servidor.setSalarios(salariosOrdenados);
		}
        
        mView.addObject("servidores", servidores);
        return mView;
    }
    
    @RequestMapping(value="/vereadores", method=RequestMethod.GET)
    public ModelAndView getVereadores() {
        ModelAndView mView = new ModelAndView("vereadores");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Vereador");
        
        for (Servidor servidor : servidores) {
        	//Obtem a lista de salarios ordenados pela data, sendo o primeiro da lista o mais atual.
        	List<Salario>salariosOrdenados;
        	salariosOrdenados = DAOSalario.getAllSalaryOfAServer(servidor.getId());
        	servidor.setSalarios(salariosOrdenados);
		}
        
        mView.addObject("servidores", servidores);

        return mView;
    }
    
    @RequestMapping(value="/servidores/prefeitura", method=RequestMethod.GET)
    public ModelAndView getServidoresPrefeitura() {
        ModelAndView mView = new ModelAndView("servidoresPrefeitura");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Servidor-Prefeitura");
        
        for (Servidor servidor : servidores) {
        	//Obtem a lista de salarios ordenados pela data, sendo o primeiro da lista o mais atual.
        	List<Salario>salariosOrdenados;
        	salariosOrdenados = DAOSalario.getAllSalaryOfAServer(servidor.getId());
        	servidor.setSalarios(salariosOrdenados);
		}
        
        mView.addObject("servidores", servidores);

        return mView;
    }    

    @RequestMapping(value="/servidores/camara", method=RequestMethod.GET)
    public ModelAndView getServidoresCamara() {
        ModelAndView mView = new ModelAndView("servidoresCamara");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Servidor-Camara");
        
        for (Servidor servidor : servidores) {
        	//Obtem a lista de salarios ordenados pela data, sendo o primeiro da lista o mais atual.
        	List<Salario>salariosOrdenados;
        	salariosOrdenados = DAOSalario.getAllSalaryOfAServer(servidor.getId());
        	servidor.setSalarios(salariosOrdenados);
		}
        
        mView.addObject("servidores", servidores);
 
        return mView;
    }
    
    @RequestMapping(value="/historico/{id}",method=RequestMethod.GET)
    public ModelAndView getHistoricoSalarial(@PathVariable("id") Long id) {
    	ModelAndView mView = new ModelAndView("historico_salarial");
    	Servidor servidor = new Servidor();
    	
    	servidor = mtMogi.findById(id);
    	List<Salario>salarios = new ArrayList<Salario>();
    	salarios = DAOSalario.getAllSalaryOfAServer(id);
    	
    	mView.addObject("servidor", servidor);
    	mView.addObject("salarios", salarios);
    	
    	
    	return mView;
    }
}
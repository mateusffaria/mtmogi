package br.com.mtmogi.mtmogi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.ServiceImpl.MtmogiServiceImpl;

@Controller
public class SalarioController {

	@Autowired
    MtmogiServiceImpl mtMogi;
    
    @Autowired
    SalarioDAO DAOSalario;
	
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
    
    @RequestMapping(value="/salario/comparar")
    public ModelAndView compararSalarios() {
    	
    	ModelAndView mView = new ModelAndView("salario_compare");
    	
    	ArrayList<Long> serversIds = new ArrayList<Long>();
    	List<Servidor> servers= new ArrayList<Servidor>();
    	
    	serversIds.add(200000L);
    	serversIds.add(200001L);
    	
    	servers = mtMogi.findServers(serversIds);
    	
    	mView.addObject("servidores", servers);
    	
    	
    	return mView;
    	
    }
	
}

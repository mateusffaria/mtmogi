package br.com.mtmogi.mtmogi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.SalarioDesconto;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.MtmogiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MtmogiController {

	@Autowired
	MtmogiService mtMogi;

	@Autowired
	SalarioDAO DAOSalario;

	/**
	 * @return
	 */
	@RequestMapping(value = "/prefeito", method = RequestMethod.GET)
	public ModelAndView getServidores() {
		ModelAndView mView = new ModelAndView("prefeito");
		Iterable<Servidor> servidores = mtMogi.findByCargoLike("PREFEITO");

		for (Servidor servidor : servidores) {
			List<SalarioDesconto> salarioFinal = new ArrayList<SalarioDesconto>();
			SalarioDesconto salario = new SalarioDesconto();
			servidor.setSalarios(DAOSalario.getAllGrossIncoming(servidor.getId()));
			
			double somatoria = servidor.getSalarios().stream().map(SalarioDesconto::getValor)
					.mapToDouble(BigDecimal::doubleValue).sum();

			salario.setValor(new BigDecimal(somatoria).setScale(2, RoundingMode.HALF_DOWN));
			salarioFinal.add(salario);
			servidor.setSalarios(salarioFinal);
			
		}

		mView.addObject("servidores", servidores);
		return mView;
	}

	@RequestMapping(value = "/vereadores", method = RequestMethod.GET)
	public ModelAndView getVereadores() {
		ModelAndView mView = new ModelAndView("vereadores");
		Iterable<Servidor> servidores = mtMogi.findByCargoLike("VEREADOR");

		for (Servidor servidor : servidores) {
			// Obtem a lista de salarios ordenados pela data, sendo o primeiro da lista o
			// mais atual.
			List<SalarioDesconto> salariosOrdenados;
			salariosOrdenados = DAOSalario.getAllSalaryOfAServer(servidor.getId());
			servidor.setSalarios(salariosOrdenados);
		}

		mView.addObject("servidores", servidores);

		return mView;
	}

	@RequestMapping(value = "/servidores/prefeitura", method = RequestMethod.GET)
	public ModelAndView getServidoresPrefeitura() {
		ModelAndView mView = new ModelAndView("servidoresPrefeitura");
		Iterable<Servidor> servidores = mtMogi.findAll();

		mView.addObject("servidores", servidores);

		return mView;
	}

    @RequestMapping(value="/servidores/camara", method=RequestMethod.GET)
    public ModelAndView getServidoresCamara() {
        ModelAndView mView = new ModelAndView("servidoresCamara");
        Iterable<Servidor> servidores = mtMogi.findByCargoLike("Servidor-Camara");

		mView.addObject("servidores", servidores);

		return mView;
	}
    
	@RequestMapping(value = "/servidores/teste", method = RequestMethod.GET)
	public ModelAndView getServidoresTeste() {
		ModelAndView mView = new ModelAndView("teste");
		
		return mView;
	}
    
}
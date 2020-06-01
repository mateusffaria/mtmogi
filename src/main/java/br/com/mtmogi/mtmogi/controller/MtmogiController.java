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
		List<Servidor> servidores = mtMogi.findByCargoLike("PREFEITO");
		salarioBrutoServidores(servidores);
		mView.addObject("servidores", servidores);
		return mView;
	}

	@RequestMapping(value = "/vereadores", method = RequestMethod.GET)
	public ModelAndView getVereadores() {
		ModelAndView mView = new ModelAndView("vereadores");
		List<Servidor> servidores = mtMogi.findByCargoLike("VEREADOR");

		mView.addObject("servidores", servidores);

		return mView;
	}

	@RequestMapping(value = "/servidores/prefeitura", method = RequestMethod.GET)
	public ModelAndView getServidoresPrefeitura() {
		ModelAndView mView = new ModelAndView("servidoresPrefeitura");
		List<Servidor> servidores = mtMogi.findAll();
		salarioBrutoServidores(servidores);
		mView.addObject("servidores", servidores);

		return mView;
	}

	@RequestMapping(value = "/servidores/camara", method = RequestMethod.GET)
	public ModelAndView getServidoresCamara() {
		ModelAndView mView = new ModelAndView("servidoresCamara");
		List<Servidor> servidores = mtMogi.findByCargoLike("Servidor-Camara");

		mView.addObject("servidores", servidores);

		return mView;
	}

	private void salarioBrutoServidores(List<Servidor> servidores) {
		for (Servidor servidor : servidores) {
			List<SalarioDesconto> salariosOrdenados = new ArrayList<>();
			SalarioDesconto salarioBruto = new SalarioDesconto();
			salarioBruto.setValor(DAOSalario.getAllGrossIncoming(servidor.getId()).get(0));
			salariosOrdenados.add(salarioBruto);
			servidor.setSalarios(salariosOrdenados);
		}
	}
}
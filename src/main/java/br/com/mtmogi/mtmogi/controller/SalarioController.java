package br.com.mtmogi.mtmogi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.SalarioDesconto;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.ServiceImpl.MtmogiServiceImpl;

@Controller
public class SalarioController {

	@Autowired
	MtmogiServiceImpl mtMogi;

	@Autowired
	SalarioDAO DAOSalario;

	@RequestMapping(value = "/historico/{id}", method = RequestMethod.GET)
	public ModelAndView getHistoricoSalarial(@PathVariable("id") Long id) {
		ModelAndView mView = new ModelAndView("historico_salarial");
		Servidor servidor = new Servidor();

		servidor = mtMogi.findById(id);
		List<SalarioDesconto> salarios = new ArrayList<SalarioDesconto>();
		salarios = DAOSalario.getAllSalaryOfAServer(id);

		mView.addObject("servidor", servidor);
		mView.addObject("salarios", salarios);

		return mView;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/salario/comparar")
	public ModelAndView compararSalarios(HttpSession session, RedirectAttributes redirectAttributes) {

		ModelAndView mView = new ModelAndView("salario_compare");

		List<Servidor> servers = new ArrayList<Servidor>();
		ArrayList<Long> idServers = (ArrayList<Long>) session.getAttribute("servers");

		// Map with data to generated the graphic
		Map<String, Double> salarios = new LinkedHashMap<String, Double>();
		Map<String, Double> descontos = new LinkedHashMap<String, Double>();

		// is the list null?
		if (idServers != null) {

			servers = mtMogi.findServers(idServers);

			for (Servidor s : servers) {
				salarios.put(s.getNome(),
						DAOSalario.getAllGrossIncoming(s.getId()).stream().mapToDouble(BigDecimal::doubleValue).sum());
			}

			for (Servidor s : servers) {
				descontos.put(s.getNome(), DAOSalario.getAllDiscountSalary(s.getId()).stream()
						.mapToDouble(BigDecimal::doubleValue).sum());
			}

			// servers = mtMogi.findServers(idServers);
			mView.addObject("servidores", servers);
			mView.addObject("salarios", salarios);
			mView.addObject("descontos", descontos);
			mView.setViewName("salario_compare");
			return mView;

		} else {

			redirectAttributes.addFlashAttribute("mensagem", "Desculpe, Houve algum erro na exibição dos resultados");
			mView.setViewName("redirect:/prefeito");
			return mView;
		}

	}

}

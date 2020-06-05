package br.com.mtmogi.mtmogi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import br.com.mtmogi.mtmogi.dao.SalarioDAO;
import br.com.mtmogi.mtmogi.model.TipoServidor;
import br.com.mtmogi.mtmogi.service.MtmogiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		mView.addObject("typeServer", TipoServidor.PREFEITO);
		
		return mView;
	}

	@RequestMapping(value = "/vereadores", method = RequestMethod.GET)
	public ModelAndView getVereadores() {
		
		ModelAndView mView = new ModelAndView("vereadores");
		mView.addObject("typeServer", TipoServidor.VEREADOR);

		return mView;
	}

	@RequestMapping(value = "/servidores/prefeitura", method = RequestMethod.GET)
	public ModelAndView getServidoresPrefeitura() {
		
		ModelAndView mView = new ModelAndView("servidoresPrefeitura");
		mView.addObject("typeServer", TipoServidor.SERVIDORES_EM_GERAL);	
 
		return mView;
	}

    @RequestMapping(value="/servidores/camara", method=RequestMethod.GET)
    public ModelAndView getServidoresCamara() {
        
    	ModelAndView mView = new ModelAndView("servidoresCamara");
    	mView.addObject("typeServer", TipoServidor.SERVIDOR_CAMARA);
        
		return mView;
	}
    
}
package br.com.mtmogi.mtmogi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.MtmogiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class MtmogiController {

    @Autowired
    MtmogiService mtMogi;

    @RequestMapping(value="/prefeito", method=RequestMethod.GET)
    public ModelAndView getServidores() {
        ModelAndView mView = new ModelAndView("prefeito");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Prefeito");
        mView.addObject("servidores", servidores);
        System.out.println(servidores);

        return mView;
    }
    
    @RequestMapping(value="/vereadores", method=RequestMethod.GET)
    public ModelAndView getVereadores() {
        ModelAndView mView = new ModelAndView("vereadores");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Vereador");
        mView.addObject("servidores", servidores);
        System.out.println(servidores);

        return mView;
    }
    
    @RequestMapping(value="/servidores/prefeitura", method=RequestMethod.GET)
    public ModelAndView getServidoresPrefeitura() {
        ModelAndView mView = new ModelAndView("servidoresPrefeitura");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Servidor-Prefeitura");
        mView.addObject("servidores", servidores);
        System.out.println(servidores);

        return mView;
    }    

    @RequestMapping(value="/servidores/camara", method=RequestMethod.GET)
    public ModelAndView getServidoresCamara() {
        ModelAndView mView = new ModelAndView("servidoresCamara");
        List<Servidor> servidores = mtMogi.findByFuncaoLike("Servidor-Camara");
        mView.addObject("servidores", servidores);
        System.out.println(servidores);

        return mView;
    }    
}
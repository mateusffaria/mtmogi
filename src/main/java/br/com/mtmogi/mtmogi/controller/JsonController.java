package br.com.mtmogi.mtmogi.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.service.ServiceImpl.MtmogiServiceImpl;




@RestController
@RequestMapping("/service")
public class JsonController {

	@Autowired
	MtmogiServiceImpl mService;
	
	@RequestMapping(value = "/compare",method = RequestMethod.POST)
	public @ResponseBody ModelAndView upload(@RequestBody String payload) {
		
		ModelAndView mView = new ModelAndView("redirect:/vereadores");
		
		//Gson object instance
		Gson gson = new Gson();
		
		//Defining an object type for conversion
		Type collectionType = new TypeToken<List<Long>>() {}.getType();
		
		//Populating long array with defined object type
		ArrayList<Long> idServers = gson.fromJson(payload, collectionType);
		
		//Server list instance
		List<Servidor> servers = new ArrayList<Servidor>();
		
		//Populating Server array
		servers = mService.findServers(idServers);
		
		mView.addObject("servidores", servers);
		
		return mView;
	}
	
}

package br.com.mtmogi.mtmogi.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public String upload(@RequestBody String payload) {
		
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
		
		for (Servidor servidor : servers) {
			System.out.println(servidor.getNome() + " " + servidor.getFuncao());
		}
		
		return "OK";
	}
	
}

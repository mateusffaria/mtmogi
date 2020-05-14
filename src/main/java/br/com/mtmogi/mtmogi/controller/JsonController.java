package br.com.mtmogi.mtmogi.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;


@RestController
@RequestMapping("/service")
public class JsonController {

	@RequestMapping(value = "/compare",method = RequestMethod.POST)
	public String upload(@RequestBody String payload) {
		
		Gson gson = new Gson();
		
		@SuppressWarnings("unchecked")
		ArrayList<Long> idServers = gson.fromJson(payload, ArrayList.class);
		
		for (Object object : idServers) {
			System.out.println(object);
		}
		
		return "OK";
	}
	
}

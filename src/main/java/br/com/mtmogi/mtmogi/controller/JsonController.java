package br.com.mtmogi.mtmogi.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/service")
public class JsonController {

	@RequestMapping(value = "/compare",method = RequestMethod.POST)
	public String upload(@RequestBody String payload) {
		
		System.out.println(payload);
		return "OK";
	}
	
}

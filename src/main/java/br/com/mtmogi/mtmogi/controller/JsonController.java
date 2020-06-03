package br.com.mtmogi.mtmogi.controller;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.mtmogi.mtmogi.dto.ServidorSalarioDTO;
import br.com.mtmogi.mtmogi.dto.ServidoresDTO;
import br.com.mtmogi.mtmogi.service.ServiceImpl.MtmogiServiceImpl;

@RestController
@RequestMapping("/service")
public class JsonController {
	
	@Autowired
	MtmogiServiceImpl mService;
	
	@RequestMapping(value = "/compare",method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestBody String payload, HttpSession session) {
	
		//Gson object instance
		Gson gson = new Gson();
		
		//Defining an object type for conversion
		Type collectionType = new TypeToken<List<Long>>() {}.getType();
		
		//Populating long array with defined object type
		ArrayList<Long> idServers = gson.fromJson(payload, collectionType);
		
		session.setAttribute("servers", idServers);
		
		return "OK";
	}

	@GetMapping("/get/servers")
	public ServidoresDTO getServer(@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("draw") int draw,
			@RequestParam("order[0][column]") int sortColIndex,
			@RequestParam("order[0][dir]") String order,
			@RequestParam("columns[0][data]") String col0DataAttrName) {
		
		System.out.println(sortColIndex + order + col0DataAttrName);
		
		int totalServers = mService.totalServers();
		
		ServidoresDTO servidores = new ServidoresDTO();
		List<ServidorSalarioDTO> servidoresDto = builderServidorSalarioList(order, start, length);
		
		servidores.setData(servidoresDto);
		servidores.setDraw(draw);
		servidores.setRecordsFiltered(totalServers);
		servidores.setRecordsTotal(totalServers);
		
		return servidores;
		
	}
	
	private List<ServidorSalarioDTO> builderServidorSalarioList(String order, int start, int length) {
		
		List<ServidorSalarioDTO> servidores = new ArrayList<ServidorSalarioDTO>();
		
		Iterator<Object> iterator = mService.findServersWithPage(order, start, length).iterator(); 
				
				while(iterator.hasNext()) {
					
					Object[] obj = (Object[]) iterator.next();
					
					ServidorSalarioDTO servidor = new ServidorSalarioDTO();
					
					Double salario = Double.valueOf(obj[4].toString());
					
					servidor.setId(Integer.valueOf(obj[0].toString()));
					servidor.setCargo(obj[1].toString());
					servidor.setNome(obj[2].toString());
					servidor.setRgf(obj[3].toString());
					servidor.setSalario(BigDecimal.valueOf(salario).setScale(2, RoundingMode.HALF_EVEN));
					
					servidores.add(servidor);
				}
				
		return servidores;
	}
}

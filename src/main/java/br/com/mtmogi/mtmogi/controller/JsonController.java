package br.com.mtmogi.mtmogi.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.mtmogi.mtmogi.dao.DataTablesDAO;
import br.com.mtmogi.mtmogi.dto.ServidorSalarioDTO;
import br.com.mtmogi.mtmogi.dto.ServidoresDTO;
import br.com.mtmogi.mtmogi.service.ServiceImpl.MtmogiServiceImpl;

@RestController
@RequestMapping("/service")
public class JsonController {
	
	@Autowired
	MtmogiServiceImpl mService;
	
	@Autowired
	DataTablesDAO DAODataTable;
	
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

	@GetMapping("/get/servers/{tipo}")
	public ServidoresDTO getServer(@PathVariable("tipo") String tipo,
			@RequestParam("start") int start,
			@RequestParam("length") int length,
			@RequestParam("draw") int draw,
			@RequestParam("order[0][column]") int sortColIndex,
			@RequestParam("order[0][dir]") String order,
			@RequestParam("columns[0][data]") String col0DataAttrName) {
		
		//instance of the necessary objects
		int totalServers = DAODataTable.countTotalServers(tipo);
		ServidoresDTO servidores = new ServidoresDTO();
		List<ServidorSalarioDTO> servidoresDto = DAODataTable.findServersWithPage(tipo, sortColIndex, order, start, length); 
		
		//Sending data to table
		servidores.setData(servidoresDto);
		servidores.setDraw(draw);
		servidores.setRecordsFiltered(totalServers);
		servidores.setRecordsTotal(totalServers);
		
		return servidores;		
	}
	
}
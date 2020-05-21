package br.com.mtmogi.mtmogi.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import br.com.mtmogi.mtmogi.dto.ServidorDTO;
import br.com.mtmogi.mtmogi.model.Salario;
import br.com.mtmogi.mtmogi.model.Servidor;
import br.com.mtmogi.mtmogi.repository.MtmogiRepository;
import br.com.mtmogi.mtmogi.repository.SalarioRepositorio;
import br.com.mtmogi.mtmogi.utils.GeneralJsonInfo;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Controller
public class CrawlerController {

	private final RestTemplate restTemplate;
	private final MtmogiRepository servidorRepositorio;
	private final SalarioRepositorio salarioRepositorio;
	
	@GetMapping("/admin/force_update")
	public @ResponseBody String teste() {
		String teste = restTemplate.getForObject("http://www.licitacao.pmmc.com.br/Transparencia/vencimentos2", String.class);
		List<Servidor> servidores = new ArrayList<>();
		List<Salario> salarios = new ArrayList<>();
		
		Gson json = new Gson();
		GeneralJsonInfo generalInfo = json.fromJson(teste, GeneralJsonInfo.class);
		
		for (ServidorDTO servidor : generalInfo.servidores) {
			Servidor funcionario = new Servidor();
			Salario salario_funcionario = new Salario();
			String salario = "";
			
			funcionario.setNome(servidor.getNome());
			funcionario.setCargo(servidor.getCargo());
			funcionario.setRgf(servidor.getRgf());
			
			salario = servidor.getRendimentos().replace(".", "");
			salario = salario.replace(",", ".");
			
			salario_funcionario.setValor(new BigDecimal(salario));
			salario_funcionario.setDataAtualizacao(Calendar.getInstance());
			salario_funcionario.setServidor(funcionario);
			
			salarios.add(salario_funcionario);
			servidores.add(funcionario);
		}

		servidorRepositorio.saveAll(servidores);
		salarioRepositorio.saveAll(salarios);

		return "Update complete";
	}

}

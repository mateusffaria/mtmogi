package br.com.mtmogi.mtmogi.utils;

import java.util.List;

import br.com.mtmogi.mtmogi.dto.SalarioDescontosDTO;

public class GeneralJsonInfoSalario {
	public String referencia;
	public String cargo;
	public String nome;
	public String regime;
	public List<SalarioDescontosDTO> rendimentos, descontos, outros;
}

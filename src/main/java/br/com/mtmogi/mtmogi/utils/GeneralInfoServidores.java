package br.com.mtmogi.mtmogi.utils;

import java.util.List;

import br.com.mtmogi.mtmogi.dto.ServidorDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralInfoServidores {
	private List<ServidorDTO> servidores;
	private String referencia;
}

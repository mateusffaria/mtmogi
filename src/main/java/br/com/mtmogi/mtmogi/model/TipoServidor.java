package br.com.mtmogi.mtmogi.model;

public enum TipoServidor {

	PREFEITO("Prefeito"),
	VEREADOR("Vereador"),
	SERVIDOR_CAMARA("Servidor Camara"),
	SERVIDOR_PREFEITURA("Servidor-Prefeitura"),
	SERVIDORES_EM_GERAL("");
	
	private String descricao;

	private TipoServidor(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}


package br.com.mtmogi.mtmogi.dto;

import java.util.List;

public class ServidoresDTO {

	public int draw;
	public int recordsTotal;
	public int recordsFiltered;

	public List<ServidorSalarioDTO> data;

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<ServidorSalarioDTO> getData() {
		return data;
	}

	public void setData(List<ServidorSalarioDTO> data) {
		this.data = data;
	}

	
}

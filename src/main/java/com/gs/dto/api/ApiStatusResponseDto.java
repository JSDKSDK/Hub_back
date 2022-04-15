package com.gs.dto.api;

public class ApiStatusResponseDto {
	private String mensaje;
	private String folio;

	public ApiStatusResponseDto() {
	}

	public ApiStatusResponseDto(String mensaje, String folio) {
		this.mensaje = mensaje;
		this.folio = folio;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

}

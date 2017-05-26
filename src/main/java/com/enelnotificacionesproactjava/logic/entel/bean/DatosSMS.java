package com.enelnotificacionesproactjava.logic.entel.bean;

import com.enelnotificacionesproactjava.controller.Task;


public class DatosSMS {
	
	private String telefono;
	private Integer tipo_campania;
	private Integer id_externo;
	private String mensaje;
	private String iso_3166;
	


	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getTipo_campania() {
		return tipo_campania;
	}

	public void setTipo_campania(Integer tipo_campania) {
		this.tipo_campania = tipo_campania;
	}

	public Integer getId_externo() {
		return id_externo;
	}

	public void setId_externo(Integer id_externo) {
		this.id_externo = id_externo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	public String getIso_3166() {
		return iso_3166;
	}

	public void setIso_3166(String iso_3166) {
		this.iso_3166 = iso_3166;
	}

	
	public static DatosSMS copyFieldsFromHerokuToDatosSMSBean(Task datosTarea) {
		DatosSMS datosSMS = new DatosSMS();
		if (datosTarea != null) {
			datosSMS.setTelefono((datosTarea.getPhone() != null && !"".equals(datosTarea.getPhone()) ? datosTarea.getPhone() : ""));
		}
		return datosSMS;
	}
}
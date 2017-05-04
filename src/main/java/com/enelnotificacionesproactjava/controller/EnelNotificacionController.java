package com.enelnotificacionesproactjava.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;




/**
 * Metodo al que se accede tras hacer submit en el login. Si el usuario existe nos lleva al jsp Hello y se guarda un User en session.
 * Si el usuario no existe, se muestra en mensaje en la pagina login.
 * 
 * @param username
 * @param password
 * @param request
 * @param response
 * @return
 */




@Controller
public class EnelNotificacionController {

	@RequestMapping(value = "/jsonSimulation", method = RequestMethod.GET)
	public ModelAndView listadoCuentas() {

		ModelAndView model = new ModelAndView();
		model.setViewName("private/jsonSimulation");
			
		return model;
	}

	@POST
	@RequestMapping("/execute")
	@Consumes(MediaType.APPLICATION_JSON)
	public String  RestMkcService(InputStream incomingData) {
		System.out.println("Entramos en envio JSON");
		System.out.println("JSON recibido::: "+ incomingData.toString());
		BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
		System.out.println("Lectura JSON recibido::: "+ in);


		return null;
	}
}

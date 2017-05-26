package com.enelnotificacionesproactjava.controller;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enelnotificacionesproactjava.logic.entel.rest.EnviarSMS;

/**
 * Metodo  encargado de recibir la informacion de las Tareas enviadas por la app de HEroku enelNotificacionProact por servicio Rest
 * hola
 * hola Sergio
 */
@Controller
@RequestMapping("/execute")
public class EnelNotificacionController {
	
	 final static Logger logger = Logger.getLogger(EnelNotificacionController.class);
	
	 @RequestMapping(value = "/getTaskInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	 public  @ResponseBody Integer getMCTaskJson(@RequestBody Task task) throws Exception {
		 logger.info("Tarea->Nombre Contacto:::" + task.getName());
		 logger.info("Tarea->Telefono:::" + task.getPhone());
		 System.out.println("Tarea->Nombre Contacto:::" + task.getName());
		 System.out.println("Tarea->Telefono:::" + task.getPhone());
		 
		 try{
			 System.out.println("Comenzamos llamada al servicio de ENTEL");
			 EnviarSMS.createCaseInSalesforce(task);
		 }
		 catch(Exception e){
			 System.out.println("Ha habido un error en " + task.getPhone());
		 }
		 
		 return null;
	}
}


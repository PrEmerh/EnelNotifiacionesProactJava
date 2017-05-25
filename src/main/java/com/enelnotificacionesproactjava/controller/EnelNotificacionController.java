package com.enelnotificacionesproactjava.controller;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

/**
 * Metodo  encargado de recibir la informacion de las Tareas enviadas por la app de HEroku enelNotificacionProact por servicio Rest
 * 
 */
@Controller
@RequestMapping("/execute")
public class EnelNotificacionController {
	
	 final static Logger logger = Logger.getLogger(EnelNotificacionController.class);
	
	 @RequestMapping(value = "/getTaskInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
	 public @ResponseBody Long addProvider(@RequestBody Task task) {
		 logger.info("Tarea->Nombre Contacto:::" + task.getName());
		 logger.info("Tarea->Telefono:::" + task.getPhone());
		 System.out.println("Tarea->Nombre Contacto:::" + task.getName());
		 System.out.println("Tarea->Nombre Contacto:::" + task.getPhone());
		 return null;
	}
}


package com.enelnotificacionesproactjava.controller;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enelnotificacionesproactjava.logic.entel.bean.DatosSMS;
import com.enelnotificacionesproactjava.logic.entel.bean.InArguments;
import com.enelnotificacionesproactjava.logic.entel.bean.RequestObject;
import com.enelnotificacionesproactjava.logic.entel.rest.EnviarSMS;


/**
 * Author:sasensio@deloitte.es
 * Metodo  encargado de recibir la informacion de las Tareas enviadas por la app de Heroku enelNotificacionProact por servicio Rest
 * 
 * 
 */

@Controller
@RequestMapping("/rest-activity")
public class EnelNotificacionController {
	
 final static Logger logger = Logger.getLogger(EnelNotificacionController.class);
	
 @RequestMapping(value = "/execute", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
 public  @ResponseBody Integer getMCTaskJson(@RequestBody RequestObject task) throws Exception {
	 
	 //logger.info("Tarea->Telefono:::" + task.getSecondaryPhone__c());
	 
	 System.out.println("HEMOS LLEGADO A LA APLICACION JAVA :) ");

	 System.out.println("Task ActivityObjectID::::" + task.getActivityObjectID());
	 
	 //Setteamos campos
	 
	 String mensaje=null;
	 String externalID=null;
	 String phone=null;
	 String secondaryphone=null;
	 String mobilephone=null;
	 
     for(InArguments inArguments:task.getInArguments() ){
     	if(inArguments.getMessage()!=null && inArguments.getMessage()!="" ){
     		mensaje=inArguments.getMessage();
     	}
     	if(inArguments.getTaskExternalID()!=null && inArguments.getTaskExternalID()!="" ){
     		externalID=inArguments.getTaskExternalID();
     	}
     	if(inArguments.getPhone()!=null && inArguments.getPhone()!="" ){
     		phone=inArguments.getPhone();
     	}
     	if(inArguments.getSecondaryphone()!=null && inArguments.getSecondaryphone()!="" ){
     		secondaryphone=inArguments.getSecondaryphone();
     	}
     	if(inArguments.getMobilephone()!=null && inArguments.getMobilephone()!="" ){
     		mobilephone=inArguments.getMobilephone();
     	}
     }

	 System.out.println("Task inArguments::::" + task.getInArguments());
		 
	 System.out.println("Task ExternalID ::::" + externalID);
	 
	 System.out.println("Telefono contacto::::" + phone);
	 
	 System.out.println("Telefono secundario contacto::::" + secondaryphone);
	 
	 System.out.println("Movil contacto::::" + mobilephone);
	 
	 System.out.println("Task Message::::" + mensaje);
	 
	 
	 //Mapeamos campos a enviar a servicio REST
	 
	 DatosSMS datosms= new DatosSMS();
	 
		 //Setteamos idExterno y mensaje 
		 
		 if(externalID!=null && externalID!=""){
			 datosms.setIdExterno(externalID);
		 }	 
		 if(mensaje!=null && mensaje!=""){
			 datosms.setMensaje(mensaje);
		 }
		 //Setteamos medio comunicacion de mensaje

		 if(phone!=null && phone!=""){			 
			 datosms.setNumero(phone);
			 System.out.println("Medio de envio Telefono principal::::" + phone);
		 }
		 else if(secondaryphone!=null && secondaryphone!=""){
			 datosms.setNumero(secondaryphone);
			 System.out.println("Medio de envio Telefono secundario::::" + secondaryphone);
		 }
		 else if(mobilephone!=null && mobilephone!=""){
			 datosms.setNumero(mobilephone);
			 System.out.println("Medio de envio Movil::::" + mobilephone);
		 }
	 	 
	 //Enviamos datos al servicio REST
	 
	 try{
		 System.out.println("Comenzamos llamada al servicio de ENTEL");
		 EnviarSMS.createCaseInSalesforce(datosms);
	 }
	 catch(Exception e){
		 System.out.println("Ha habido un error en " + e);
		 }	 
	 return null;	 
	}			
}

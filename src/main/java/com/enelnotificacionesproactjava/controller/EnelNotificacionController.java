package com.enelnotificacionesproactjava.controller;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;




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
	 @RequestMapping("/execute")
	 @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
	 public JSONObject sayPlainTextHello(JSONObject inputJsonObj) throws Exception {

	    String input = (String) inputJsonObj.get("input");
	    String output = "The input you sent is :" + input;
	    JSONObject outputJsonObj = new JSONObject();
	    outputJsonObj.put("output", output);
	    return outputJsonObj;
	  }
}

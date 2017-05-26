package com.enelnotificacionesproactjava.logic.entel.rest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.PasswordAuthentication;
import java.net.URL;
import org.apache.log4j.Logger;
import org.postgresql.core.Utils;
import org.springframework.web.util.UriUtils;

import com.enelnotificacionesproactjava.logic.entel.bean.DatosSMS;
import com.enelnotificacionesproactjava.logic.entel.bean.InArguments;
import com.enelnotificacionesproactjava.logic.entel.bean.RequestObject;
import com.enelnotificacionesproactjava.util.constants.Constantes;


  public class EnviarSMS {
	final static Logger logger = Logger.getLogger(EnviarSMS.class);
	
	public static void createCaseInSalesforce(DatosSMS datossms) throws Exception {		
	                  	            
	            String urlString ="http://ws.econecta.cl/api/citas/envio?tipo_campania=91&iso_3166=CHL&id_externo=externalIdField&telefono=numberField&mensaje=messageField";
	            
	            //Mapeamos campos dinamicos URL
	            
	            if(datossms!=null){
		            if(datossms.getMensaje()!=null && datossms.getMensaje()!="" ){
		            	urlString=urlString.replaceAll("messageField", UriUtils.encode(datossms.getMensaje(), "UTF-8"));
		            }
		            if(datossms.getIdExterno()!=null && datossms.getIdExterno()!="" ){
		            	urlString=urlString.replaceAll("externalIdField", datossms.getIdExterno());
		            }
		            if(datossms.getNumero()!=null && datossms.getNumero()!="" ){
		            	urlString=urlString.replaceAll("numberField", datossms.getNumero());
		            }
	            }

		        try {
		            Authenticator.setDefault(new CustomAuthenticator());
		            URL url = new URL (urlString);	            
		            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		            
		            connection.setRequestMethod("GET");
		            connection.setDoOutput(true);
		            InputStream content = (InputStream)connection.getInputStream();
		            
		            System.out.println("Codigo Respuesta a REST::::::  "+ connection.getResponseCode());
		            System.out.println("Mensaje Respuesta a REST::::::  "+ connection.getResponseMessage());
		            System.out.println("URL que invocamos::::::  "+ connection.getURL());
	
		            BufferedReader in   = 
		            new BufferedReader (new InputStreamReader (content));
		            String line;
		            while ((line = in.readLine()) != null) {
		                System.out.println(line);
		            }
		            connection.disconnect();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
		        finally{
		        	
		        }
		
		    }	
	
  }

  class CustomAuthenticator extends Authenticator {
    // Called when password authorization is needed
    protected PasswordAuthentication getPasswordAuthentication() {        
        // Get information about the request
        String prompt = getRequestingPrompt();
        String hostname = getRequestingHost();
        InetAddress ipaddr = getRequestingSite();
        int port = getRequestingPort();
        String username = Constantes.ENTEL_USERNAME;
        String password = Constantes.ENTEL_PASSWORD;
        // Return the information (a data holder that is used by Authenticator)
        return new PasswordAuthentication(username, password.toCharArray());        
    }     
}

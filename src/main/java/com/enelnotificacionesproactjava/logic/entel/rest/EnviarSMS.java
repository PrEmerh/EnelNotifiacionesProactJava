package com.enelnotificacionesproactjava.logic.entel.rest;
 
import java.io.IOException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
 
import com.enelnotificacionesproactjava.controller.Task;
import com.enelnotificacionesproactjava.util.constants.Constantes;
//import com.fasterxml.jackson.databind.ObjectMapper;
 
 
public class EnviarSMS {
         final static Logger logger = Logger.getLogger(EnviarSMS.class);
        
         public static void createCaseInSalesforce(Task datosMC) throws Exception {          
        
 
                   HttpClient httpClient = null;
                   HttpGet get = null;
                   //ObjectMapper mapper = new ObjectMapper();
                  
                   try {
                           
 
                            logger.trace("Inicio crear caso");
                           
                            // SIN AUTENTICACION HTTP BASIC
                            //httpClient = HttpClientBuilder.create().build();
                           
                           
                            // CON AUTENTICACION HTTP BASIC - INICIO
                            CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials(Constantes.ENTEL_USERNAME,Constantes.ENTEL_PASSWORD);
                            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
                           
                           
                            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
                            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                            httpClient = httpClientBuilder.build();
                           
                            // CON AUTENTICACION HTTP BASIC - FIN
                           
                            get = new HttpGet(Constantes.ENTEL_REST_URL_ENVIAR+"?tipo_campania=91&iso_3166=CHL&id_externo=EX_ID_ENTEL_0001&telefono=56952086757&mensaje=PruebaSMSDeloitte");
                           
                           
                            logger.info("Intento de llamada POST crearCaso");
                           
                            HttpResponse response = httpClient.execute(get);
                            HttpEntity entity = response.getEntity();
                            String entityResponse = EntityUtils.toString(entity);
                           
                            logger.info("Respuesta: " + entityResponse);
                            logger.info("Status: " + response.getStatusLine());
 
 
                   } catch(IOException exception) {
                            logger.error("Error llamada a servicio REST", exception);
                            throw new Exception("Error llamada a servicio REST", exception);
                   } finally {
                            if (get != null) {
                                      get.releaseConnection();
                            }
                   }
                  
         }
        
}

package com.enelnotificacionesproactjava.logic.entel.rest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.enelnotificacionesproactjava.controller.Task;
import com.enelnotificacionesproactjava.logic.entel.bean.DatosSMS;
import com.enelnotificacionesproactjava.logic.entel.response.EnviarSMSResponse;
import com.enelnotificacionesproactjava.util.constants.Constantes;
//import com.fasterxml.jackson.databind.ObjectMapper;


public class EnviarSMS {
	final static Logger logger = Logger.getLogger(EnviarSMS.class);
	
	public static void createCaseInSalesforce(Task datosMC) throws Exception {		

		HttpClient httpClient = null;
		HttpPost post = null;
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
			
			post = new HttpPost(Constantes.ENTEL_REST_URL_ENVIAR);

			
			DatosSMS datosSMS = DatosSMS.copyFieldsFromHerokuToDatosSMSBean(datosMC);
			//String jsonInString = mapper.writeValueAsString(datosSMS);
			String jsonInString = "";
			logger.info("Parseo JSON datosSMS: " + jsonInString);

			StringEntity params = new StringEntity(jsonInString, "UTF-8");
			post.setEntity(params);
			
			logger.info("Intento de llamada POST crearCaso");
			
			HttpResponse response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			String entityResponse = EntityUtils.toString(entity);
			
			logger.info("Respuesta: " + entityResponse);
			logger.info("Status: " + response.getStatusLine());

//			enviarSMSResponse = mapper.readValue(entityResponse, EnviarSMSResponse.class);
//			if (enviarSMSResponse != null && !"0".equals(enviarSMSResponse.getControlErrores().getCodigoError())) {
//				logger.error(ConstantesError.SALESFORCE_CASE_CREATION_ERROR);
//				logger.error("Codigo: " + createCaseResponse.getControlErrores().getCodigoError() + ". Mensaje: " + enviarSMSResponse.getControlErrores().getMensajeError());
//				throw new EmergenciasException(enviarSMSResponse.getControlErrores().getCodigoError(), enviarSMSResponse.getControlErrores().getMensajeError());
//			}

		} catch(IOException exception) {
			logger.error("Error llamada a servicio REST", exception);
			throw new Exception("Error llamada a servicio REST", exception);
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		
	}
}
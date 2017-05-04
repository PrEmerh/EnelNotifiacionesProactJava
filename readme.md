	* Configuración datasource:
	
	Necesitamos tener definidas como variables de entorno:
		DATABASE_URL_JAVA : jdbc:postgresql://host:puerto/databaseName
		DATABASE_USER : Usuario de la base de datos
		DATABASE_PASSWORD : Password de la base de datos
	En Heroku por defecto viene definida la variable de entorno DATABASE_URL, pero la forma en que se define esta url de conexcion no funciona para Hibernate. 
	
	* Multilenguaje:
	
	Los textos de todos los jsp deben localizarse dentro de src/main/resources y tienen que llamarse "text_page_lang", donde lang son las letras que definen el idioma, por ejemplo _es para español.
	Para el cambio de idioma de la aplicacion seria suficiente con añadir en los jsp: 
			<a href="changLang.do?lang=en">English</a>|<a href="changLang.do?lang=es">Español</a> 
	Tenemos que ir al servler 'LanguageServlet' para modificar la url y evitar que nos de problemas las url en las navegaciones siguientes
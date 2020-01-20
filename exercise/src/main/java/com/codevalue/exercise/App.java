package com.codevalue.exercise;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App 
{
	private  static final Logger logger = Logger.getLogger(App.class);

	public static void main( String[] args ) throws  Exception
	{

		BasicConfigurator.configure();
 
		logger.info( "App is Running .." );
		SpringApplication.run(App.class, args);
	}
}

package com.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//Se requiere extender SpringBootServletInitializer para correr con JBOSS 7.3
@SpringBootApplication
public class SistemaAdministradorDeAccesosQaApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SistemaAdministradorDeAccesosQaApplication.class, args);
	}

}

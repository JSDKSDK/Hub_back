package com.gs.configuration;

import java.time.Duration;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class PersistenceConfig {
	@Autowired
	Environment environment;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		builder.setConnectTimeout(Duration.ofMillis(60 * 1000));
		builder.setReadTimeout(Duration.ofMillis(60 * 1000));
		return builder.build();
	}

	private final String URL = "oracle.datasource.url";
	private final String USER = "oracle.datasource.username";
	private final String DRIVER = "oracle.datasource.driver-class-name";
	private final String PASSWORD = "oracle.datasource.password";

// TODO: Descomentar driverManagerDataSource, para correr con Spring Boot este toma los datos del applications properties

	@Bean
	@Profile("dev")
	DataSource driverManagerDataSourceDev() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(environment.getProperty(URL));
		driverManagerDataSource.setUsername(environment.getProperty(USER));
		driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
		driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
		return driverManagerDataSource;

	}

// TODO: Actualizar perfil prod, para correr con en JBOSS desarrollo, QA, Producción este toma el jdni del standalone

	@Bean
	@Profile("jboss")
	public DataSource jndiDataSourceProd() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:/usra");
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();

	}

}

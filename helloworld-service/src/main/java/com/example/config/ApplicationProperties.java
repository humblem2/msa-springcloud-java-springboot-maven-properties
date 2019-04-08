package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties specific to Application.
 * TODO: 분석 @Component, @ConfigurationProperties
 * <p>
 *     Properties are configured in the application.yml file.
 * </p>
 */
@Component
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Service service = new Service();

    public Service getService() {
        return service;
    }
    
    /*
    	backend: helloworld-backend-2
    */
    public static class Service {
        
        private String backend = "helloworld-backend-2";

		public String getBackend() {
			return backend;
		}

		public void setBackend(String backend) {
			this.backend = backend;
		}   
    }  
}

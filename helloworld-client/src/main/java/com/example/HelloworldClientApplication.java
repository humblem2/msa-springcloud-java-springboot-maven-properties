package com.example;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.example.config.DefaultProfileUtil;
import com.example.filters.pre.SimpleFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class HelloworldClientApplication {
/*
	public static void main(String[] args) {
		SpringApplication.run(HelloworldClientApplication.class, args);
	}
*/
	private static final Logger log = LoggerFactory.getLogger(HelloworldClientApplication.class);
	
    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(HelloworldClientApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        Environment env = app.run(args).getEnvironment();
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) { 
            protocol = "https";
        }
        log.info("\n\n--------------------------------------------------------------------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\t{}://localhost:{}\n\t" +
                        "External: \t{}://{}:{}\n\t" +
                        "Profile(s): \t{}\n--------------------------------------------------------------------------------------------------------------------",
                env.getProperty("spring.application.name"),
                protocol,
                env.getProperty("server.port"),
                protocol,
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
       
        String configServerStatus = env.getProperty("spring.cloud.config.uri"); 
        log.info("\n--------------------------------------------------------------------------------------------------------------------\n\t" +
		                "Config Server: \t\t\t\t{}\n\t" +
		                "Config Server Health: \t\t\t{}\n\t" +
		                "Current Profile From Config Server: \t{}\n--------------------------------------------------------------------------------------------------------------------" ,
		        (configServerStatus == null ? "Not found or not setup for this application" : configServerStatus), 
		        (configServerStatus + "/health"), 
		        (env.getActiveProfiles() == null 
		        ? "Not found Active-Profile for this application" 
		        : configServerStatus + "/" + env.getProperty("spring.application.name") + "/" + env.getActiveProfiles()[0]));

        log.info("\n--------------------------------------------------------------------------------------------------------------------\n\t" +
        		"[Project Logger Level Infos]: \n\t");
        log.error	("-- error	레벨 [logback]");
        log.warn	("-- warn	레벨 [logback]");
        log.info	("-- info	레벨 [logback]");
        log.trace	("-- trace	레벨 [logback]");
        log.debug	("-- debug	레벨 [logback]");
        log.info("\t\n--------------------------------------------------------------------------------------------------------------------\n");
        
	} // end of main
	
	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
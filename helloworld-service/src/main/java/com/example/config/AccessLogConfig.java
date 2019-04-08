package com.example.config;


import org.apache.catalina.valves.AccessLogValve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by chosoohyun on 17/08/2018
 * @see https://stackoverflow.com/questions/28483064/how-do-i-configure-the-location-and-name-of-tomcat-access-log-in-spring-boot/28486711#28486711
 * @see https://blog.perfectacle.com/2018/07/22/spring-boot-2-log/
 * @see https://tomcat.apache.org/tomcat-8.5-doc/config/valve.html#Access_Logging
 * @see https://tomcat.apache.org/tomcat-8.5-doc/config/valve.html
 */

@Configuration
public class AccessLogConfig extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {
	private static final Logger log = LoggerFactory.getLogger(AccessLogConfig.class);
    
	@Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof TomcatEmbeddedServletContainerFactory) {
            TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) container;
            AccessLogValve accessLogValve = new AccessLogValve();
            accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%s\t%r\t%{User-Agent}i\t%{Referer}i\t%{X-Forwarded-For}i\t%b");
            accessLogValve.setDirectory(".");
            accessLogValve.setSuffix(".log");
            accessLogValve.setCondition("ignoreLogging");
            containerFactory.addContextValves(accessLogValve);
        } else {
        	log.error("WARNING! this customizer does not support your configured container");
        }
    }

}

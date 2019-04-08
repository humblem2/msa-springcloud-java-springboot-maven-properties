package com.example.config;


import org.apache.catalina.valves.AccessLogValve;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chosoohyun on 17/08/2018
 * @see https://stackoverflow.com/questions/28483064/how-do-i-configure-the-location-and-name-of-tomcat-access-log-in-spring-boot/28486711#28486711
 * @see https://blog.perfectacle.com/2018/07/22/spring-boot-2-log/
 * @see https://tomcat.apache.org/tomcat-8.5-doc/config/valve.html#Access_Logging
 * @see https://tomcat.apache.org/tomcat-8.5-doc/config/valve.html
 */
@Configuration
public class AccessLogConfig implements WebServerFactoryCustomizer {
    @Override
    public void customize(final WebServerFactory factory) {
        final TomcatServletWebServerFactory containerFactory = (TomcatServletWebServerFactory) factory;
        final AccessLogValve accessLogValve = new AccessLogValve();
        //accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%s\t%r\t%{User-Agent}i\t%{Referer}i\t%a\t%b");
        accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%s\t%r\t%{User-Agent}i\t%{Referer}i\t%{X-Forwarded-For}i\t%b");
        accessLogValve.setDirectory(".");
        accessLogValve.setSuffix(".log");
        accessLogValve.setCondition("ignoreLogging");
        containerFactory.addContextValves(accessLogValve);
    }
}

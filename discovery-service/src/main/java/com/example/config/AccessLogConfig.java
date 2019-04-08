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
            /*
            	NOTE:
            	
            	%{yyyy-MM-dd HH:mm:ss}t - 날짜
            	%s - status code
            	%r - First line of the request (method and request URI)
            	%{User-Agent}i - user agent(browser, 검색 엔진 등등)
            	%{Referer}i - Referer 헤더
            	%a - remote ip address
            	%b - 몇 바이트 응답했는지(Content-Length랑 똑같을 거 같다.)
            */
            /*
	            NOTE:
	            
	            L4 switch/Proxy Server를 쓰는 경우에는 remote ip에 고정된 l4/proxy ip가 찍히게 되므로
	            원래 클라이언트의 IP를 알기 위해선 %a를 %{X-Forwarded-For}i로 고쳐줘야한다.
	            %{X-Forwarded-For}i를 사용하게 되면 remote ip address, l4/proxy ip address 순서로 찍히게 된다.
	
	            directory를 세팅해주지 않으면 server.tomcat.basedir로 정한 디렉토리 안에 또 logs 디렉토리가 생겨서 지저분해서 세팅했다.
	            또한 suffix를 세팅해주지 않으면 access_log.2018-07-23와 같이 파일명이 저장되서 log 파일 extension을 붙여주는 코드를 세팅했다.
	            condition은 request.getAttribute(“ignoreLogging”)의 값이 null이 아니면 logging 하는 것이고,
	            conditionIf는 request.getAttribute(“ignoreLogging”)의 값이 null이면 logging 하는 것이다.
	            무시할만한 로그가 많다면 일일이 set하는 건 비효율적이므로 conditionIf를 쓰면 되고,
	            무시할만한 로그가 적다면 set 하는 게 더 효율적이므로 condition을 쓰면 좋을 것 같다.
            */
        } else {
        	log.error("WARNING! this customizer does not support your configured container");
        }
    }

}

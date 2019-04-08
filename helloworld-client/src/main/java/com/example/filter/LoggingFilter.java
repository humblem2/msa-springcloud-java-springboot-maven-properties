package com.example.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

/**
 * TODO: 엣지만 해놓고 나머지 서비스(예컨데 백엔드서비스)는 할 필요 없나
 * URL을 분석해서 attribute를 세팅하는 Filter
 * Created by chosoohyun on 17/08/2018
 * @see https://blog.perfectacle.com/2018/07/22/spring-boot-2-log/
 */
@Component
public class LoggingFilter implements Filter{
    @Override
    public void doFilter(	final ServletRequest 	req,
                         	final ServletResponse 	res,
                         	final FilterChain 		chain) throws IOException, ServletException {
        final String url = ((HttpServletRequest) req).getRequestURI();
        
        if(url.matches("/(health|.+\\.(ico|js))")) {
            req.setAttribute("ignoreLogging", true);
        }
        
        chain.doFilter(req, res);
    }
    
    @Override
    public void init(FilterConfig filterConfig) {}
    
    @Override
    public void destroy() {}
    
    /*    
    url을 분석해서 /health이거나, ico, js 확장자인 경우에 로깅하지 않게 세팅했다.
    저 정규표현식에 매칭되지 않는 url만 로깅하겠단 뜻이다.

    실제로 남은 access log를 보면 health check url이나 favicon.ico 등의 로그는 남아있지 않는다.
    */
}

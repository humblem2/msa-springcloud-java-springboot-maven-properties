package com.example.service.handler;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RestTemplateErrorHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException
    {
        log.error("Response error: {} {}", response.getStatusCode(), response.getStatusText());
        //throw new MyCustomException(response.getStatusText(), response.getStatusCode());
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException
    {
        if ( (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR)
                || (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR))
        {
            return true;
        }
        return false;
    }
}

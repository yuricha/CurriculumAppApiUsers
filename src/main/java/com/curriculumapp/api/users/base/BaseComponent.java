package com.curriculumapp.api.users.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BaseComponent {

	 private final Logger logger = LoggerFactory.getLogger(BaseComponent.class);

	    protected static HttpHeaders headersJson;
	    protected HttpHeaders headersText;
	    protected HttpStatus status;
	    protected ResponseDto response;
	    protected String trazabilidad;



	    public BaseComponent() {
	        headersJson = new HttpHeaders();
	        headersText = new HttpHeaders();
	        headersJson.add("Content-Type", "application/json; charset=utf-8");
	        headersText.add("Content-Type", "application/text");
	    }


	    public HttpHeaders getHeadersText() {
	        return headersText;
	    }

	    public HttpHeaders getHeadersJson() {
	        return headersJson;
	    }

	    public Logger getLogger() {
	        return logger;
	    }

}

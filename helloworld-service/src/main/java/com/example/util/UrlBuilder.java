package com.example.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlBuilder {
	private final Logger log = LoggerFactory.getLogger(UrlBuilder.class);

	String service;
	String resource;
	String login;
	int length;
	int start;
	String ids;
	
	public UrlBuilder service(String service) {
		this.service = service;
		return this;
	}
	
	public UrlBuilder resource(String resource) {
		this.resource = resource;
		return this;
	}
	
	public UrlBuilder login(String login) {
		this.login = login;
		return this;
	}
	
	public UrlBuilder ids(String ids) {
		this.ids = ids;
		return this;
	}
	
	public String build() {
		StringBuilder sb = new StringBuilder();
		
        sb
        .append("http://")
        .append(service)
        .append(resource.startsWith("/") ? "" : "/")
        .append(resource);

        //if(login != null) {
        //    sb.append("?login=").append(login);
        //}
        
        //if(ids != null){
        //	sb.append("&ids=").append(this.ids); // ids가 null 아니여야만 쿼리스트링 생성 - Fix SH 실제 원인 400ERROR response ==> 잘못된 URL
        //}
        
        //if(criterias != null) {
            // /*if(criterias.hasOneFilteredColumn())*/ {
        	//	sb.append("&filter=").append(criterias.getSearch());
            //}
            //sb.append("&size=").append(criterias.getLength());
            //sb.append("&page=").append(criterias.getStart()/criterias.getLength());
            //if(criterias.hasOneSortedColumn()) {
            //	criterias.getSortedColumnDefs().forEach(v -> {
            //		sb.append("&sort=").append(v.getName()).append(",").append(v.getSortDirection());
            //	});
            //}
        //}
            
        log.debug(">> {}",sb.toString());

		return sb.toString();
	}
}



package com.example.config;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket scp_api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("scp-core-data").apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example"))  
				//.paths(PathSelectors.ant("/user/*"))
				.build()
				.apiInfo(apiInfo())
				.useDefaultResponseMessages(false)
                .globalResponseMessage(
                					RequestMethod.GET, newArrayList(
                												new ResponseMessageBuilder()
                												.code(500)
                												.message("500 메세지 블라")
                												.responseModel(new ModelRef("Error"))
                												.build(), 
                												
                												new ResponseMessageBuilder()
                												.code(404)
                												.message("404 메세지 블라")
                												.responseModel(new ModelRef("Error"))
                												.build(),
                												
                												new ResponseMessageBuilder()
                												.code(400)
                												.message("400 메세지 블라")
                												.responseModel(new ModelRef("Error"))
                												.build(),
                												
                												new ResponseMessageBuilder()
                												.code(401)
                												.message("401 메세지 블라")
                												.responseModel(new ModelRef("Error"))
                												.build(),
                												
                												new ResponseMessageBuilder()
                												.code(403)
                												.message("403 Forbidden~")
                												.responseModel(new ModelRef("에러"))
                												.build()
                													)
                					);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("SCP-CORE-DATA API").description("One Service Portal Core DATA API")
				.termsOfServiceUrl("http://localhost")
				.contact("Soohyun Cho , lullaby@osstem.com")
				// .license("Apache License Version 2.0")
				// .licenseUrl("https://github.com/IBM-Bluemix/news-aggregator/blob/master/LICENSE")
				.version("0.1").build();
	}
	
	// 접속 URL : http://localhost:8787/swagger-ui.html
	
	// 참고 : http://jojoldu.tistory.com/31
	
	// 출처: https://github.com/eugenp/tutorials/blob/master/spring-security-rest/src/main/java/org/baeldung/spring/SwaggerConfig.java#L14
	/*
	private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("My REST API", "Some custom description of API.", "API TOS", "Terms of service", new Contact("John Doe", "www.example.com", "myeaddress@company.com"), "License of API", "API license URL", Collections.emptyList());
        return apiInfo;
    }
	*/
	
}

//출처: http://springboot.tistory.com/24 [스프링부트는 사랑입니다]
/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
}
*/


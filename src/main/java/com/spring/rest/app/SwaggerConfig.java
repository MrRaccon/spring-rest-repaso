package com.spring.rest.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.spring.rest.app.controllers"))
				.paths(PathSelectors.any())
//				.paths(PathSelectors.ant("/users/*"))
				.build()
				.useDefaultResponseMessages(false)
				;
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Proyecto de repaso")
				.version("1.0.0")
				.license("MIT")
				.contact(new Contact("Alexis Biñuelo", "https://www.linkedin.com/in/alexisbinuelo/", "noEmail"))
				.build();
	}

}

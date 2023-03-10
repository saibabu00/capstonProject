package com.stackroute.favouriteservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postsApi() {

	return new Docket(DocumentationType.SWAGGER_2).groupName("Team-07").apiInfo(apiInfo()).select()

	.paths(regex("/api/v1.*")).build();

	}
	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("Favorite service")

		.description("Favorite service API Documentation Generateed Using SWAGGER2 for our Cplayer Rest API")

		.termsOfServiceUrl("")

		.license("Bhanu")

		.licenseUrl("").version("1.0").build();

		}

}

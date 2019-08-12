package com.conf.bookingsystem.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:9989/v2/api-docs - to view the api-docs
 * http://localhost:9989/swagger-ui.html
 * @author SonaSach
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	Contact contact = new Contact("Sonal Gupta", "http://www.sonalgupta_appdeveloper.com", "sonalgupta06cs@gmail.com");

	List<VendorExtension> vendorExtensions = new ArrayList<>();

	ApiInfo apiInfo = new ApiInfo("Conference Booking RESTful Web Service documentation",
			"This pages documents Conference Booking RESTful Web Service endpoints", "1.0", "http://www.google.com",
			contact, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// .protocols(new HashSet<>(Arrays.asList("HTTP, HTTPs")))
				.apiInfo(apiInfo)
				.produces(DEFAULT_PRODUCES_AND_CONSUMES)
				.consumes(DEFAULT_PRODUCES_AND_CONSUMES)
				.select().apis(RequestHandlerSelectors.basePackage("com.conf.bookingsystem")).paths(PathSelectors.any())
				.build();
	}

}

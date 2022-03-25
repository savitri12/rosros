package com.ros.accounting;

import java.util.ArrayList;
import java.util.List;

import com.ros.accounting.safesummary.mapper.SafeSummaryMapper;
import com.ros.accounting.safesummary.mapper.SafeSummaryMapperImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import com.ros.accounting.cashup.mapper.RestDtoMapper;
import com.ros.accounting.cashup.mapper.RestDtoMapperImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;

@SpringBootApplication
@EnableEurekaClient
//@EnableResourceServer
@OpenAPIDefinition(info = @Info(title = "AccountingServiceAPI", version = "1.0", description = "API for Accounting Service"))
public class AccountingServiceApplication {


	@Value(value = "${swagger.url}")
	public String url;
	
	public static void main(String[] args) {
		SpringApplication.run(AccountingServiceApplication.class, args);
	}

	@Bean
	public RestDtoMapper mapper() {
		return new RestDtoMapperImpl();
	}
	@Bean
	public SafeSummaryMapper SafeSummaryMapper(){
		return new SafeSummaryMapperImpl();
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
	    Server server = new Server();
	    List<Server>servers = new ArrayList();
	    server.setUrl(url);
	    servers.add(server);
	    OpenAPI openAPI =new OpenAPI();
	    openAPI.setServers(servers);
	    
	    return openAPI;
//	    return new OpenAPI().servers(List.of(server));
	}
}

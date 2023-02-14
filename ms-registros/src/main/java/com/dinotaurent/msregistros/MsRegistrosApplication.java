package com.dinotaurent.msregistros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class MsRegistrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRegistrosApplication.class, args);
	}

}

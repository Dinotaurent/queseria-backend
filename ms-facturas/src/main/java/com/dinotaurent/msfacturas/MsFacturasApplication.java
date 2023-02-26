package com.dinotaurent.msfacturas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan({"com.dinotaurent.mscommonsproductosfactura.models.entity"})
@EnableFeignClients
public class MsFacturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFacturasApplication.class, args);
	}

}

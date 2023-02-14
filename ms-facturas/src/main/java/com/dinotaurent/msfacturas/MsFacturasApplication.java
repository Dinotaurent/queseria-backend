package com.dinotaurent.msfacturas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.dinotaurent.mscommonsproductosfactura.models.entity"})
public class MsFacturasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsFacturasApplication.class, args);
	}

}

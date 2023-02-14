package com.dinotaurent.msproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.dinotaurent.mscommonsproductosfactura.models.entity"})
public class MsProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsProductosApplication.class, args);
	}

}

package com.yh.erp;

import com.yh.erp.infrastructure.firebase.FirebaseProperties;
import com.yh.erp.infrastructure.properties.FileProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FirebaseProperties.class,
		FileProperties.class
})
public class ErpApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

}

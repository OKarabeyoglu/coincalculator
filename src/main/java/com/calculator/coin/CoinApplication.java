package com.calculator.coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EntityScan("com.calculator.coin.domain")
@EnableSwagger2
public class CoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinApplication.class, args);
	}

}

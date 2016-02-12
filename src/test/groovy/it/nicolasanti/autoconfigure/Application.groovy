package it.nicolasanti.autoconfigure

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.EnableTransactionManagement

@ComponentScan
@EnableAutoConfiguration @EnableTransactionManagement(proxyTargetClass=true)
class Application {
	static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args)
	}
}

package com.ipbyhj.dev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP 사용 애노테이션 : @EnableAspectJAutoProxy @EnableConfigurationProperties
 * @author cgw981
 *
 */
@EnableAspectJAutoProxy
@EnableConfigurationProperties
@SpringBootApplication
public class IntegratedProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegratedProjectApplication.class, args);
	}

}

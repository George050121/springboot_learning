package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan; // 1. 导入这个包

@SpringBootApplication //核心注解，开启自动配置和组件扫描。结合了@Configuration、@EnableAutoConfiguration和@ComponentScan三个注解的功能。
@MapperScan("com.example.demo.mapper") // 2. 加上这行，指定 Mapper 所在的包路径
public class DemoApplication {

	public static void main(String[] args) {
		//启动Spring Boot应用程序，创建Spring应用上下文并启动内嵌的服务器（如Tomcat）。
		SpringApplication.run(DemoApplication.class, args);
	}

}

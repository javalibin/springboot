package com.school.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.school.manage.mapper")
@SpringBootApplication
public class MainApplication {
		public static void main(String[] args) {
			SpringApplication.run(MainApplication.class, args);
		}
}

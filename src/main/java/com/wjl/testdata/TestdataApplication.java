package com.wjl.testdata;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
@MapperScan("com.wjl.testdata.mapper")
public class TestdataApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestdataApplication.class, args);
	}

}

package com.qifangli.edumanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.qifangli.edumanage.dao.mapper")
public class EdumanageApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EdumanageApplication.class, args);
	}

}

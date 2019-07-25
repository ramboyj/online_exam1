package com.onlineExam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;

@MapperScan(basePackages = {"com.onlineExam.dao"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Converter<Long, Date> addNewConvert() {
		return new Converter<Long, Date>() {
			@Override
			public Date convert(Long source) {
				Date date = null;
				try {
					date = new Date(source);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return date;
			}
		};
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return (container -> {
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			container.addErrorPages(error404Page);
		});
	}
}

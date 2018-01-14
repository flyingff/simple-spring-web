package edu.xjtu.demo.spring.config;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.gson.Gson;

@Configuration
@EnableWebMvc
/** 此处包名为放controller的包 */
@ComponentScan("edu.xjtu.demo.spring.controller")
public class WebConfig {
	/** JSON视图解析器 */
	@Bean
	public ViewResolver jsonViewResolver(Gson g) {
		JsonViewResolver resolver = new JsonViewResolver(g);
		resolver.setOrder(1);
		return resolver;
	}
	/** 文件上传相关 */
	@Bean
	public MultipartResolver multipartResolver(ServletContext context) {
		return new StandardServletMultipartResolver();
	}
}

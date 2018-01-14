package edu.xjtu.demo.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xjtu.demo.spring.mapper.UserMapper;

/***
 * Controller代表与URL绑定的函数，作用一般是解析请求，转发给后台处理逻辑，
 * 并把结果组织为合适的“视图”，传递给客户端。
 *
 */
@Controller
public class DemoController {
	
	/** 代表该函数与哪个URL绑定 */
	@RequestMapping("/hello")
	/** 代表函数的返回值直接作为HTTP请求的返回体 */
	@ResponseBody
	public String sayHello() {
		return "Hello from spring MVC";
	}
	
	// 以下为数据库使用方法
	@Autowired
	private UserMapper mapper;
	
	@RequestMapping("/db")
	@ResponseBody
	public List<Map<String, String>> testDB() {
		return mapper.selectAll();
	}
}

package edu.xjtu.demo.spring.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitialzer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/** Root配置 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class};
	}
	/** MVC配置 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebConfig.class};
	}
	/** 请求URL配置，凡是符合返回的形式的URL都被spring拦截处理； 
	 *  匹配规则参照Servlet的url-pattern属性
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/api/*"};
	}
	
	/** 字符编码过滤器（用于防止乱码） */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		CharacterEncodingFilter encFilter = new CharacterEncodingFilter();
		encFilter.setEncoding("utf-8");
		encFilter.setForceEncoding(true);
		servletContext.addFilter("characterFilter", encFilter).addMappingForUrlPatterns(null, false, "/*");
	}
	/** multi-part文件上传配置 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
}

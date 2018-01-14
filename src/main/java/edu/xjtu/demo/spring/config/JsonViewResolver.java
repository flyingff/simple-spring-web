package edu.xjtu.demo.spring.config;

import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 一个返回JSON的视图解析器
 * @author mg
 */
public class JsonViewResolver implements ViewResolver, Ordered {
	private JsonView view, viewErr;
	private int order = 0;
	@Override
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public JsonViewResolver(Gson g) {
		view = new JsonView(g, false);
		viewErr = new JsonView(g, true);
	}
	@Override
	public View resolveViewName(String viewName, Locale locale)
	        throws Exception {
		switch (viewName) {
		case Const.VIEW_JSON:
			return view;
		case Const.VIEW_JSON_ERROR:
			return viewErr;
		default:
			return null;
		}
	}
	
	/***
	 * Json视图的具体实现
	 * @author mg
	 */
	static class JsonView implements View {
		private Gson g;
		private boolean isError;
		public JsonView(Gson g, boolean isError) {
			this.g = g;
			this.isError = isError;
		}
		@Override
		public String getContentType() {
			return "application/json";
		}

		@Override
		public void render(Map<String, ?> model, HttpServletRequest request,
		        HttpServletResponse response) throws Exception {
			// filter for some special properties
			response.setContentType(getContentType());
			response.setCharacterEncoding("utf-8");
			
			String json;
			if(isError) {
				response.setStatus(500);
				JsonObject objErr = new JsonObject();
				objErr.addProperty("err", Optional.ofNullable(model.get(Const.VIEW_ERROR_CODE))
						.map(x->(Integer)x)
						.orElse(-1));
				Object detail = model.get(Const.VIEW_ERROR_DETAIL);
				if(detail != null) {
					objErr.addProperty("detail", String.valueOf(detail));
				}
				json = objErr.toString();
			} else {
				model.keySet().removeIf(x->x.startsWith("org.springframework"));
				json = g.toJson(model);
			}
			try(Writer wr = response.getWriter()) {
				wr.write(json);
			}
		}
	}
}
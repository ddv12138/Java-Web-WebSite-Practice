package GlobalUtils.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import java.io.File;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RootConfig.class, DataSourceConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{WebConfig.class, SecurityConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		File tmp = new File("C:/springupload/");
		if (!tmp.exists()) {
			tmp.mkdirs();
		}
		registration.setMultipartConfig(
				new MultipartConfigElement(tmp.getAbsolutePath(),
						5 * 1024 * 1024, 6 * 1024 * 1024, 0));
	}

	@Bean
	public MultipartResolver getMultipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new CharacterEncodingFilter("UTF-8")};
	}

	/*
	 *添加自定义filter和servlet或者listener可以在这里注册，如果是注册到dispatherservlet的就不用在这里注册，直接重写 getServletFilters() 即可
	 * */
//	@Override
//	public void onStartup(ServletContext context) throws ServletException {
//		super.onStartup(context);
////		FilterRegistration.Dynamic filterRegistration = context.addFilter("encodeFilter",new EncodeFilter());
////		filterRegistration.addMappingForUrlPatterns(null,false,"/**");
//	}
}

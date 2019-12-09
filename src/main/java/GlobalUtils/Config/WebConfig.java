package GlobalUtils.Config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"WebComponent"})
public class WebConfig implements WebMvcConfigurer {
//	@Bean // 配置生成模板解析器
//	@Primary
//	public ITemplateResolver templateResolver(ApplicationContext applicationContext) {
//		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//		resolver.setApplicationContext(applicationContext);
//		resolver.setPrefix("classpath:/templates/");
//		resolver.setSuffix(".html");
//		resolver.setTemplateMode(TemplateMode.HTML);
//		resolver.setCharacterEncoding("UTF-8");
//		resolver.setCacheable(false);
//		resolver.setCheckExistence(true);
//		return resolver;
//	}
//
//	@Bean // 生成模板引擎并为模板引擎注入模板解析器
//	public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//		templateEngine.setTemplateResolver(templateResolver);
//		templateEngine.addDialect(new SpringSecurityDialect());
//		return templateEngine;
//	}

	//	@Bean // 生成视图解析器并未解析器注入模板引擎
//	public ViewResolver viewResolver(TemplateEngine templateEngine) {
//		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//		viewResolver.setContentType("text/html");
//		viewResolver.setTemplateEngine((ISpringTemplateEngine) templateEngine);
//		viewResolver.setCache(false);
//		viewResolver.setCacheUnresolved(false);
//		viewResolver.setCharacterEncoding("UTF-8");
//		return viewResolver;
//	}

	/**
	 * 这里有个坑，SpringBoot2 必须重写该方法，否则静态资源无法访问
	 * 参照 https://blog.csdn.net/zhang_Red/article/details/81739005
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/META-INF/resources/")
				.addResourceLocations("classpath:/resources/")
				.addResourceLocations("classpath:/static/")
				.addResourceLocations("classpath:/public/");
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		//创建消息转换器
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//创建配置类
		com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
		//返回内容的过滤
		config.setSerializerFeatures(
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteMapNullValue,
				SerializerFeature.WriteNullListAsEmpty
		);
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		supportedMediaTypes.add(MediaType.IMAGE_GIF);
		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
		supportedMediaTypes.add(MediaType.IMAGE_PNG);
		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
		supportedMediaTypes.add(MediaType.TEXT_HTML);
		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
		supportedMediaTypes.add(MediaType.TEXT_XML);
		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
		fastConverter.setFastJsonConfig(config);
		//将fastjson添加到视图消息转换器列表内
		converters.add(fastConverter);
	}

}

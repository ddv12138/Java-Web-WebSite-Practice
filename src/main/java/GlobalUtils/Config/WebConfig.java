package GlobalUtils.Config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@Configuration
@EnableWebMvc // 相当于<mvc:annotation-driver/>，启用注解驱动的Spring MVC,使@RequestParam、@RequestMapping等注解可以被识别
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"WebComponent"})
public class WebConfig implements WebMvcConfigurer {
//	@Bean // 配置生成模板解析器
//	public ITemplateResolver templateResolver() {
//		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
//		// ServletContextTemplateResolver需要一个ServletContext作为构造参数，可通过WebApplicationContext 的方法获得
//		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(webApplicationContext.getServletContext());
//		templateResolver.setPrefix("/WEB-INF/view/");
//		templateResolver.setSuffix(".html");
//		//这里配置成false,可以免重启刷新
//		templateResolver.setCacheable(false);
//		templateResolver.setCharacterEncoding("UTF-8");
//		// 设置模板模式,也可用字符串"HTML"代替,此处不建议使用HTML5,原因看下图源码
//		templateResolver.setTemplateMode(TemplateMode.HTML);
//		return templateResolver;
//	}

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

//	@Bean // 生成视图解析器并未解析器注入模板引擎
//	public ViewResolver viewResolver(TemplateEngine templateEngine) {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/view/");
//		viewResolver.setSuffix(".html");
//		return viewResolver;
//	}

//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//	}

//	@Override
//	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//		//创建消息转换器
//		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//		//创建配置类
//		com.alibaba.fastjson.support.config.FastJsonConfig config = new com.alibaba.fastjson.support.config.FastJsonConfig();
//		//返回内容的过滤
//		config.setSerializerFeatures(
////				SerializerFeature.DisableCircularReferenceDetect
////				SerializerFeature.WriteMapNullValue
//		);
//		List<MediaType> supportedMediaTypes = new ArrayList<>();
//		supportedMediaTypes.add(MediaType.APPLICATION_JSON);
//		supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
//		supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
//		supportedMediaTypes.add(MediaType.APPLICATION_PDF);
//		supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
//		supportedMediaTypes.add(MediaType.APPLICATION_XML);
//		supportedMediaTypes.add(MediaType.IMAGE_GIF);
//		supportedMediaTypes.add(MediaType.IMAGE_JPEG);
//		supportedMediaTypes.add(MediaType.IMAGE_PNG);
//		supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
//		supportedMediaTypes.add(MediaType.TEXT_HTML);
//		supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
//		supportedMediaTypes.add(MediaType.TEXT_PLAIN);
//		supportedMediaTypes.add(MediaType.TEXT_XML);
//		fastConverter.setSupportedMediaTypes(supportedMediaTypes);
//		fastConverter.setFastJsonConfig(config);
//		//将fastjson添加到视图消息转换器列表内
//		converters.add(fastConverter);
//	}

}
